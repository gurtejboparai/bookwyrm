package com.bookwyrm.backend.book.service;


import com.bookwyrm.backend.book.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchImpl implements SearchService {

    @Autowired
    BookService bookService;

    //These words are common in book titles, and are likely to cause false positives
    private static final String[] ARTICLES = {"the", "of", "and", "&", "or", "a"};
    //The number of results to return
    private static final int NUM_RESULTS = 10;



    //This utility function removes all articles from a list of Strings, it is not case sensitive
    private ArrayList<String> removeArticles(List<String> unfiltered){
        ArrayList<String> filtered = new ArrayList<>(unfiltered);
        for (String s : unfiltered) {
            boolean isArt = false;
            String word = s;
            int idx = 0;
            while (idx < ARTICLES.length & !isArt) {
                isArt = ARTICLES[idx].equals(word.toLowerCase());
                idx++;
            }
            if (isArt) {
                filtered.remove(word);
            }
        }
        return filtered;
    }

    //This utility function removes all articles from a Set of Strings, it is not case sensitive
    //Unfortunately It was necessary to overload this function
    private HashSet<String> removeArticles(HashSet<String> unfiltered){
        HashSet<String> filtered = new HashSet<>(unfiltered);
        for (String s : unfiltered) {
            boolean isArt = false;
            String word = s;
            int idx = 0;
            while (idx < ARTICLES.length & !isArt) {
                isArt = ARTICLES[idx].equals(word.toLowerCase());
                idx++;
            }
            if (isArt) {
                filtered.remove(word);
            }
        }
        return filtered;
    }

    /*
    * This function ensures that a word wasn't inside another word for example
    * the word "the" which is in the word "THEre"
    * */
    private List<BookDao> filterNotWords(List<BookDao> books, String actual){
        List<BookDao> filtered = new ArrayList<>(books);
        for (BookDao book : books) {
            List<String> words = Arrays.asList(book.getTitle().split(" "));
            boolean match = false;
            Iterator<String> reader = words.iterator();
            while (!match & reader.hasNext()) {
                String word = reader.next();
                match = actual.equalsIgnoreCase(word);
            }
            if (!match) {
                filtered.remove(book);
            }
        }
        return filtered;
    }

    public List<BookDao> tokenSearch(String title){
        HashSet<String> uniqueStrings = new HashSet<>();
        uniqueStrings.addAll(Arrays.asList(title.split(" ")));
        uniqueStrings = this.removeArticles(uniqueStrings);
        //get Some information on the number of words
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(title.split(" ")));
        int numAllWords = tokens.size();
        //tokens = this.removeArticles(tokens);
        int numWords = this.removeArticles(tokens).size();
        ArrayList<BookDao> suspects = new ArrayList<>();
        HashMap<String, Integer> scores = new HashMap<>();
        //Put a copy of every book that has the word in the title in one big list
        for (String stick : uniqueStrings) {
            //See the Stick? Fetch!
            suspects.addAll(this.filterNotWords(bookService.findAllBooksWithTitle(stick), stick));
        }
        //The number of times that book is in the list is how many tokens match
        //So now we need to count them
        //We will also build a map of unique bookdao while we're at it
        HashMap<String, BookDao> uSuspects = new HashMap<>();
        for (BookDao book : suspects) {
            if (scores.containsKey(book.getTitle())) {
                assert(uSuspects.containsKey(book.getTitle()));
                scores.put(book.getTitle(), scores.get(book.getTitle()) + 1);
            } else {
                int bonuses = 0;
                //Check if the book's title has the same number of tokens as the search term
                ArrayList<String> broken = new ArrayList<>(Arrays.asList(book.getTitle().split(" ")));
                if (broken.size() == numAllWords) {
                    bonuses++;
                }
                broken = this.removeArticles(broken);
                if (broken.size() == numWords) {
                    bonuses += 2;
                }
                scores.put(book.getTitle(), 1 + bonuses);
                uSuspects.put(book.getTitle(), book);
            }
        }
        //Finally we need to put the dao into a sorted list using the scores
        int numBooks = Math.min(NUM_RESULTS, uSuspects.size());
        BookDao[] shelf = new BookDao[numBooks];
        for(int i = 0; i < numBooks; i++){
            String bestTitle = "";
            int bestScore = 0;
            for (String key : uSuspects.keySet()) {
                int score = scores.get(key);
                if (score > bestScore) {
                    bestScore = score;
                    bestTitle = key;
                }
            }
            shelf[i] = uSuspects.get(bestTitle);
            uSuspects.remove(bestTitle);
            scores.remove(bestTitle);
        }
        return (Arrays.asList(shelf));
    }


    //This function is here to facilitate regression testing
    public List<BookDao> originalSearch(String title){
        return bookService.findAllBooksWithTitle(title);
    }

}
