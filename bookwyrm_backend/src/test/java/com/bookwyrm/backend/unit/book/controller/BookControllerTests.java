package com.bookwyrm.backend.unit.book.controller;

import com.bookwyrm.backend.book.controller.BookController;
import com.bookwyrm.backend.book.dao.BookDao;
import com.bookwyrm.backend.book.input.BookUpdateInput;
import com.bookwyrm.backend.book.input.BookUploadInput;
import com.bookwyrm.backend.book.payload.BookDetailSearchPayload;
import com.bookwyrm.backend.book.payload.BookSearchPayload;
import com.bookwyrm.backend.book.payload.BookUploadPayload;
import com.bookwyrm.backend.book.service.BookService;
import com.bookwyrm.backend.comment.dao.CommentService;
import com.bookwyrm.backend.review.dao.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.*;


import static org.mockito.ArgumentMatchers.any;

@SpringJUnitConfig
@SpringBootTest
public class BookControllerTests {

    @Mock
    private BookService bookService;
    @Mock
    ReviewService reviewService;
    @Mock
    CommentService commentService;
    @InjectMocks
    private BookController controller;
    @Mock
    RestTemplate restTemplate;

    @Test
    public void testHappyPath(){
        MockitoAnnotations.openMocks(this);
        Mockito.when(bookService.save(any(BookDao.class))).then((x)->{
            BookDao input = x.getArgument(0);
            input.setId("123");
            return input;
        });
        //Setup
        BookUploadInput input = new BookUploadInput();
        input.setAuthor("testAuthor");
        input.setTitle("testName");
        input.setIsbn("testIsbn");
        input.setDesc("testDesc");

        //Run
        ResponseEntity response =  controller.createBook(input);

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.OK, "Expected successful endpoint call with 200 status");
        Assert.isNull(((BookUploadPayload)response.getBody()).getMessages() , "Expected successful endpoint call with no error messages");
        Assert.notNull(((BookUploadPayload)response.getBody()).getBookId() , "Expected successful endpoint call with no error messages");
    }
    @Test
    public void testBadRequest(){
        //Setup
        BookUploadInput input = new BookUploadInput();

        //Run
        ResponseEntity response =  (new BookController()).createBook(input);

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST, "Expected failed endpoint call with 400 status");
        Assert.notEmpty(((BookUploadPayload)response.getBody()).getMessages(), "Expected failed endpoint call with error messages");
    }

    @Test
    public void testGoodSearch() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(bookService.findAllBooksWithTitle(any(String.class))).thenReturn(Arrays.asList(new BookDao("testTitle", "testAuthor", "testDesc","")));

        //Run
        ResponseEntity response =  controller.searchBookByTitle("the test book");

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.OK, "Expected successful endpoint call with 200 status");
        Assert.isNull(((BookSearchPayload)response.getBody()).getMessages() , "Expected successful endpoint call with no error messages");
    }

    @Test
    public void testEmptySearch() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(bookService.findAllBooksWithTitle(any(String.class))).thenReturn(new ArrayList<>());

        //Run
        ResponseEntity response =  controller.searchBookByTitle("the test book");

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.NOT_FOUND, "Expected failed endpoint call with 404 status");
        Assert.notNull(((BookSearchPayload)response.getBody()).getMessages() , "Expected failed endpoint call with error message");
    }

    @Test
    public void testEmptyIdSearch() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(bookService.findAllBooksWithTitle(any(String.class))).thenReturn(new ArrayList<>());

        //Run
        ResponseEntity response =  controller.searchBookById("FakeId");

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.NOT_FOUND, "Expected failed endpoint call with 404 status");
        Assert.notNull(((BookDetailSearchPayload)response.getBody()).getMessages() , "Expected failed endpoint call with error message");
    }

    @Test
    public void testGoodSearchById() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(bookService.findByBookId(any(String.class))).thenReturn(new BookDao("testTitle", "testAuthor","testDesc",""));

        //Run
        ResponseEntity response = controller.searchBookById("the test book");

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.OK, "Expected successful endpoint call with 200 status");
        Assert.isNull(((BookDetailSearchPayload)response.getBody()).getMessages() , "Expected successful endpoint call with no error messages");
    }

    @Test
    public void testGoodUpdate() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(bookService.findByBookId(any(String.class))).thenReturn(new BookDao("testTitle", "testAuthor","testDesc",""));
        BookUpdateInput bookUpdateInput = new BookUpdateInput();
        bookUpdateInput.setId("testId");
        bookUpdateInput.setGenre("testGenre");
        bookUpdateInput.setDesc("testDescUpdate");
        Map<String, Float> map = new HashMap<>();
        bookUpdateInput.setRate(map);

        //Run
        ResponseEntity response = controller.updateBookDesc(bookUpdateInput);

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.OK, "Expected successful endpoint call with 200 status");
        Assert.isNull(((BookDetailSearchPayload)response.getBody()).getMessages() , "Expected successful endpoint call with no error messages");
    }

    @Test
    public void testBadUpdate() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(bookService.findByBookId(any(String.class))).thenReturn(new BookDao("testTitle", "testAuthor","testDesc",""));
        BookUpdateInput bookUpdateInput = new BookUpdateInput();
        bookUpdateInput.setDesc("testDescUpdate");
        Map<String, Float> map = new HashMap<>();
        bookUpdateInput.setRate(map);


        //Run
        ResponseEntity response = controller.updateBookDesc(bookUpdateInput);

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST, "Expected failed endpoint call with 400 status");
        Assert.notNull(((BookDetailSearchPayload)response.getBody()).getMessages() , "Expected failed endpoint call with error messages");
    }

    @Test
    public void testGoodTopGenreSearch() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(bookService.findAll(any(Sort.class))).thenReturn(Arrays.asList(new BookDao("testTitle", "testAuthor","testDesc","")));

        //Run
        ResponseEntity response = controller.getTopRatedInGenre("Adventure");

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.OK, "Expected successful endpoint call with 200 status");
        Assert.isNull(((BookDetailSearchPayload)response.getBody()).getMessages() , "Expected successful endpoint call with no error messages");
    }

    @Test
    public void testBadTopGenreSearch() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(bookService.findAll(any(Sort.class))).thenReturn(Arrays.asList());

        //Run
        ResponseEntity response = controller.getTopRatedInGenre("Adventure");

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.NOT_FOUND, "Expected failed endpoint call with 404 status");
        Assert.notNull(((BookDetailSearchPayload)response.getBody()).getMessages() , "Expected failed endpoint call with error messages");
    }

    @Test
    public void testGoogleSearch() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(restTemplate.getForObject(any(String.class), ArgumentMatchers.eq(String.class))).thenReturn("successful");

        //Test that we get a response from google
        //Run
        ResponseEntity response = controller.searchBookGoogleApi("123");

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.OK, "Expected failed endpoint call with 400 status");
    }
}
