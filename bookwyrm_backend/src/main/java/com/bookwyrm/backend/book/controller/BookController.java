package com.bookwyrm.backend.book.controller;

import com.bookwyrm.backend.book.dao.BookDao;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

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
            BookDao newBook = new BookDao(bookUploadInput.getTitle(), bookUploadInput.getAuthor(), bookUploadInput.getDesc(), bookUploadInput.getIsbn());
            
            //Save book
            bookService.save(newBook);

            //Ensure book got saved properly
            response.loadBookDao(newBook);

        } else {
            //Inform user of error
            response.setMessages(errorList);
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(response);
    }

    @CrossOrigin
    @GetMapping("/{title}")
    public ResponseEntity searchBookByTitle(@PathVariable("title") String title) {

        BookSearchPayload response = new BookSearchPayload();
        HttpStatus status = HttpStatus.OK;
        response.setBookDaoList(bookService.findAllBooksWithTitle(title));
        if (response.getBookDaoList().isEmpty()) {
            status = HttpStatus.NOT_FOUND;
            response.setMessages(Arrays.asList("Book does not exist in the database. Please try adding the book first."));
        }

        return ResponseEntity.status(status).body(response);
    }

    @CrossOrigin
    @GetMapping("/details/{id}")
    public ResponseEntity searchBookById(@PathVariable("id") String id) {

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
}
