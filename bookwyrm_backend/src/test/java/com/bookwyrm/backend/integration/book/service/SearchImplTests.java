package com.bookwyrm.backend.integration.book.service;

import com.bookwyrm.backend.book.dao.BookDao;
import com.bookwyrm.backend.book.service.BookService;
import com.bookwyrm.backend.book.service.SearchImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class SearchImplTests {
    //For the record I eventually Started Making Things up
    private static BookDao[] fakeDB = {
            new BookDao("Romeo and Juliet", "testAuthor", "testDesc","", ""),
            new BookDao("Golden and Grey", "testAuthor", "testDesc","", ""),
            new BookDao("The Great Gatsby", "testAuthor", "testDesc","", ""),
            new BookDao("20000 leagues under the sea", "testAuthor", "testDesc","", ""),
            new BookDao("Tales From The Gas Station", "testAuthor", "testDesc","", ""),
            new BookDao("Jane Eyre", "testAuthor", "testDesc","", ""),
            new BookDao("Lord of Chaos", "testAuthor", "testDesc","", ""),
            new BookDao("Tomorrow When The War Began", "testAuthor", "testDesc","", ""),
            new BookDao("Darkness Be My Friend", "testAuthor", "testDesc","", ""),
            new BookDao("The Mist", "testAuthor", "testDesc","", ""),
            new BookDao("Where The Wild Things Are", "testAuthor", "testDesc","", ""),
            new BookDao("Generals Die In Bed", "testAuthor", "testDesc","", ""),
            new BookDao("All's Quiet on the Western Front", "testAuthor", "testDesc","", ""),
            new BookDao("Animal Farm", "testAuthor", "testDesc","", ""),
            new BookDao("The Flames of the Tiger", "testAuthor", "testDesc","", ""),
            new BookDao("Call of Cathulu", "testAuthor", "testDesc","", ""),
            new BookDao("To Kill a Mocking Bird", "testAuthor", "testDesc","", ""),
            new BookDao("The Stinky Cheese Man", "testAuthor", "testDesc","", ""),
            new BookDao("Of Mice and Men", "testAuthor", "testDesc","", ""),
            new BookDao("Lord of Flies", "testAuthor", "testDesc","", ""),
            new BookDao("World of Tomorrow", "testAuthor", "testDesc","", ""),
            new BookDao("Things that Go Bump in The Night", "testAuthor", "testDesc","", ""),
            new BookDao("Treasure Island", "testAuthor", "testDesc","", ""),
            new BookDao("A Series of Unfortunate Events", "testAuthor", "testDesc","", ""),
            new BookDao("The Great Journey", "testAuthor", "testDesc","", ""),
            new BookDao("When I Die", "testAuthor", "testDesc","", ""),
            new BookDao("Golden Eye", "testAuthor", "testDesc","", ""),
            new BookDao("Going Down In Flames", "testAuthor", "testDesc","", ""),
            new BookDao("Around the World In 88 Days", "testAuthor", "testDesc","", ""),
            new BookDao("Tales From Beyond", "testAuthor", "testDesc","", ""),
            new BookDao("The Diary of Jane", "testAuthor", "testDesc","", ""),
            new BookDao("Lord of the Rings", "testAuthor", "testDesc","", ""),
            new BookDao("Where Do I Go Now", "testAuthor", "testDesc","", ""),
            new BookDao("War and Peace", "testAuthor", "testDesc","", ""),
            new BookDao("City of Ashes", "testAuthor", "testDesc","", ""),
            new BookDao("Where Empires Go To Die", "testAuthor", "testDesc","", ""),
            new BookDao("20000 Miles On Land", "testAuthor", "testDesc","", "")
    };

    @Mock
    private BookService bookService;

    @InjectMocks
    private SearchImpl searchImpl;

    @Test
    public void testTokenSearch() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(bookService.findAllBooksWithTitle(any(String.class))).thenReturn(Arrays.asList(fakeDB));

        //Run
        int numResults = 0;
        boolean alwaysFirst = true;
        for(int i = 0; i < fakeDB.length; i++){
            String query = fakeDB[i].getTitle();

            List<BookDao> response =  searchImpl.tokenSearch(query);

            //The original Search would return at least one search result, the book with the exact title
            numResults += response.size();
            //Check that the first result has the same title as the query
            if(alwaysFirst){
                alwaysFirst = response.get(0).getTitle().equalsIgnoreCase(query);
            }
        }

        //Check results

        Assert.isTrue(numResults > fakeDB.length, "Expected more resposes than db entries");
        Assert.isTrue(alwaysFirst , "Expected first result to always be first in list");
    }

}
