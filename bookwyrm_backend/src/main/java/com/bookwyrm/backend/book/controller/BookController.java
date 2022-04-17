package com.bookwyrm.backend.book.controller;

import com.bookwyrm.backend.book.dao.BookDao;
import com.bookwyrm.backend.book.input.BookUpdateInput;
import com.bookwyrm.backend.book.input.BookUploadInput;
import com.bookwyrm.backend.book.payload.BookDetailSearchPayload;
import com.bookwyrm.backend.book.payload.BookSearchPayload;
import com.bookwyrm.backend.book.payload.BookUploadPayload;
import com.bookwyrm.backend.book.service.BookService;
import com.bookwyrm.backend.book.validator.BookValidator;
import com.bookwyrm.backend.comment.dao.CommentService;
import com.bookwyrm.backend.review.dao.ReviewDao;
import com.bookwyrm.backend.review.dao.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    //These words are common in book titles, and are likely to cause false positives
    private static final String[] ARTICLES = {"the", "of", "and", "&", "or"};
    //The number of results to return
    private static final int NUMRESULTS = 10;

    @Autowired
    BookService bookService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    CommentService commentService;
    @Autowired
    RestTemplate restTemplate;

    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookUploadPayload> createBook(
            @RequestBody BookUploadInput bookUploadInput) {
        //Check input
        List<String> errorList = BookValidator.validateUploadInformation(bookUploadInput);

        //Prepare payload
        BookUploadPayload response = new BookUploadPayload();
        HttpStatus status = HttpStatus.OK;

        if (errorList.isEmpty()) {
            //Create Book
            BookDao newBook = new BookDao(bookUploadInput.getTitle(), bookUploadInput.getAuthor(), bookUploadInput.getDesc(), bookUploadInput.getIsbn(), bookUploadInput.getGenre());
            
            //Save book
            bookService.save(newBook);

            //Ensure book got saved properly
            response.setBookId(newBook.getId());

        } else {
            //Inform user of error
            response.setMessages(errorList);
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(response);
    }

    //This utility function removes all articles from a list of Strings, it is not case sensitive
    private ArrayList<String> removeArticles(List<String> unfiltered){
        ArrayList<String> filtered = new ArrayList<String>(unfiltered);
        Iterator<String> iter = unfiltered.iterator();
        while(iter.hasNext()){
            boolean isArt = false;
            String word = iter.next();
            int idx = 0;
            while(idx < this.ARTICLES.length & !isArt){
                isArt = this.ARTICLES[idx].equals(word.toLowerCase());
                idx++;
            }
            if(isArt){
                filtered.remove(word);
            }
        }
        return filtered;
    }

    //This utility function removes all articles from a Set of Strings, it is not case sensitive
    //Unfortunately It was necessary to overload this function
    private HashSet<String> removeArticles(HashSet<String> unfiltered){
        HashSet<String> filtered = new HashSet<String>(unfiltered);
        Iterator<String> iter = unfiltered.iterator();
        while(iter.hasNext()){
            boolean isArt = false;
            String word = iter.next();
            int idx = 0;
            while(idx < this.ARTICLES.length & !isArt){
                isArt = this.ARTICLES[idx].equals(word.toLowerCase());
                idx++;
            }
            if(isArt){
                filtered.remove(word);
            }
        }
        return filtered;
    }

    @CrossOrigin
    @GetMapping("/{title}")
    public ResponseEntity<BookSearchPayload> searchBookByTitle(@PathVariable("title") String title) {

        BookSearchPayload response = new BookSearchPayload();
        HttpStatus status = HttpStatus.OK;

        HashSet<String> uTokens = new HashSet<String>();
        uTokens.addAll(Arrays.asList(title.split(" ")));
        uTokens = this.removeArticles(uTokens);
        //get Some information on the number of words
        ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(title.split(" ")));
        int numAllWords = tokens.size();
        tokens = this.removeArticles(tokens);
        int numWords = tokens.size();
        //response.setBookDaoList(bookService.findAllBooksWithTitle(title));
        ArrayList<BookDao> suspects = new ArrayList<BookDao>();
        HashMap<String, Integer> scores = new HashMap<String, Integer>();
        //Put a copy of every book that has the word in the title in one big list
        Iterator<String> searchFor = uTokens.iterator();
        while(searchFor.hasNext()){
            //See the Stick? Fetch!
            String stick = searchFor.next();
            suspects.addAll(bookService.findAllBooksWithTitle(stick));
        }
        //The number of times that book is in the list is how many tokens match
        //So now we need to count them
        //We will also build a map of unique bookdao while we're at it
        HashMap<String, BookDao> uSuspects = new HashMap<String, BookDao>();
        Iterator<BookDao> counter = suspects.iterator();
        while(counter.hasNext()){
            BookDao book = counter.next();
            if(scores.containsKey(book.getTitle())){
                scores.put(book.getTitle(), scores.get(book.getTitle()) + 1);
            }
            else{
                int bonuses = 0;
                //Check if the book's title has the same number of tokens as the search term
                ArrayList<String> broken = new ArrayList<String>(Arrays.asList(book.getTitle().split(" ")));
                if(broken.size() == numAllWords){
                    bonuses++;
                }
                broken = this.removeArticles(broken);
                if(broken.size() == numWords){
                    bonuses += 2;
                }
                scores.put(book.getTitle(), 1 + bonuses);
                uSuspects.put(book.getTitle(), book);
            }
        }
        //Finally we need to put the dao into a sorted list using the scores
        int numBooks = Math.min(this.NUMRESULTS, uSuspects.size());
        BookDao[] shelf = new BookDao[numBooks];
        for(int i = 0; i < numBooks; i++){
            String bestTitle = "";
            int bestScore = 0;
            Iterator<String> iter = uSuspects.keySet().iterator();
            while (iter.hasNext()){
                String key = iter.next();
                int score = scores.get(key);
                if (score > bestScore){
                    bestScore = score;
                    bestTitle = key;
                }
            }
            shelf[i] = uSuspects.get(bestTitle);
            uSuspects.remove(bestTitle);
            scores.remove(bestTitle);
        }
        response.setBookDaoList(Arrays.asList(shelf));
        if (response.getBookDaoList().isEmpty()) {
            status = HttpStatus.NOT_FOUND;
            response.setMessages(Arrays.asList("Book does not exist in the database. Please try adding the book first."));
        }

        return ResponseEntity.status(status).body(response);
    }

    @CrossOrigin
    @GetMapping("/details/{id}")
    public ResponseEntity<BookDetailSearchPayload> searchBookById(@PathVariable("id") String id) {

        BookDetailSearchPayload response = new BookDetailSearchPayload();
        HttpStatus status = HttpStatus.OK;
        response.setBookDao(bookService.findByBookId(id));

        if (response.getBookDao() == null) {
            status = HttpStatus.NOT_FOUND;
            response.setMessages(Arrays.asList("Book Id does not exist. Please try another ID."));
        }else{
            //Load Reviews
            List<ReviewDao> reviewList = reviewService.getReviewsByBook(response.getBookDao().getId());
            //Load Comments on Reviews
            reviewList.forEach(review ->{
                review.setCommentList(commentService.getCommentsByReview(review.getId()));
            });
            //Attach Reviews to book
            response.getBookDao().setReviewList(reviewList);
        }

        return ResponseEntity.status(status).body(response);
    }

    @CrossOrigin
    @GetMapping("/deepsearch/{isbn}")
    public ResponseEntity<String> searchBookGoogleApi(@PathVariable("isbn") String isbn) {
        return ResponseEntity.status(HttpStatus.OK).body(restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes?q=isbn:"+isbn,String.class));
    }

    @CrossOrigin
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDetailSearchPayload> updateBookDesc(@RequestBody BookUpdateInput bookUpdateInput){
        //Check input
        List<String> errorList = BookValidator.validateUpdateInformation(bookUpdateInput);

        //Prepare payload
        BookDetailSearchPayload response = new BookDetailSearchPayload();
        HttpStatus status = HttpStatus.OK;

        if (errorList.isEmpty()) {
            BookDao foundBook = bookService.findByBookId(bookUpdateInput.getId());
            if(foundBook!=null){
                //Update and save book
                foundBook.setDescription(bookUpdateInput.getDesc());
                foundBook.setAvg(bookUpdateInput.getRate());
                foundBook.setGenre(bookUpdateInput.getGenre());
                bookService.save(foundBook);

                //Add book to send back
                response.setBookDao(foundBook);
            }
        } else {
            //Inform user of error
            response.setMessages(errorList);
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(response);
    }

    @CrossOrigin
    @GetMapping("/newest")
    public ResponseEntity<BookDetailSearchPayload> getNewestBookByGenre(@PathParam("genre") String genre) {

        BookDetailSearchPayload response = new BookDetailSearchPayload();
        HttpStatus status = HttpStatus.OK;

        List<BookDao> bookDaoList = bookService.findByGenre(genre);
        if(bookDaoList!=null && !bookDaoList.isEmpty()){
            response.setBookDao(bookDaoList.get(0));
        }else{
            response.setMessages(Arrays.asList("No books found."));
            status=HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(status).body(response);
    }

    @CrossOrigin
    @GetMapping("/top")
    public ResponseEntity<BookDetailSearchPayload> getTopRatedInGenre(@PathParam("genre") String genre){

        //Prepare payload
        BookDetailSearchPayload response = new BookDetailSearchPayload();
        HttpStatus status = HttpStatus.OK;

        List<BookDao> byGenreSortedList = bookService.findAll(Sort.by(Sort.Direction.DESC,"avgRate."+genre));
        if(byGenreSortedList!=null && !byGenreSortedList.isEmpty()) {
            //Search for top
            response.setBookDao(byGenreSortedList.get(0));
        }else{
            response.setMessages(Arrays.asList("No books found."));
            status= HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(status).body(response);
    }
}
