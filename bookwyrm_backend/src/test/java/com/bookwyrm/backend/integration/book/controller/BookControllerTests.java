package com.bookwyrm.backend.integration.book.controller;

import com.bookwyrm.backend.book.controller.BookController;
import com.bookwyrm.backend.book.input.BookUploadInput;
import com.bookwyrm.backend.book.payload.BookUploadPayload;
import com.bookwyrm.backend.book.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;

@SpringJUnitConfig
public class BookControllerTests {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController controller;

    @Test
    public void testHappyPath(){
        MockitoAnnotations.openMocks(this);

        //Setup
        BookUploadInput input = new BookUploadInput();
        input.setAuthor("testAuthor");
        input.setTitle("testName");

        //Run
        ResponseEntity response =  controller.createBook(input);

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.OK, "Expected successful endpoint call with 200 status");
        Assert.isNull(((BookUploadPayload)response.getBody()).getMessages() , "Expected successful endpoint call with no error messages");
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

}
