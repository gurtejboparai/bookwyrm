package com.bookwyrm.backend.integration.review.controller;

import com.bookwyrm.backend.book.controller.BookController;
import com.bookwyrm.backend.book.dao.BookDao;
import com.bookwyrm.backend.book.service.BookService;
import com.bookwyrm.backend.review.controller.ReviewController;
import com.bookwyrm.backend.review.dao.ReviewService;
import com.bookwyrm.backend.review.input.ReviewUploadInput;
import com.bookwyrm.backend.review.payload.ReviewUploadPayload;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringJUnitConfig
@SpringBootTest
public class ReviewControllerTests {

    @Mock
    private BookService bookService;

    @InjectMocks
    private ReviewController controller;

    @Test
    public void testHappyPath(){

        MockitoAnnotations.openMocks(this);

        Mockito.when(bookService.findById(any(String.class))).thenReturn(Optional.of(new BookDao("testTitle","testAuthor")));
        //Setup
        ReviewUploadInput input = new ReviewUploadInput();
        input.setAuthor("testAuthor");
        input.setAnonymousFlag(true);
        input.setContent("testContent");
        input.setBookId("testId");

        //Run
        ResponseEntity response =  controller.createReview(input);

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.OK, "Expected successful endpoint call with 200 status");
        Assert.isNull(((ReviewUploadPayload)response.getBody()).getMessages() , "Expected successful endpoint call with no error messages");

    }
    @Test
    public void testBadRequest(){
        //Setup
        ReviewUploadInput input = new ReviewUploadInput();

        //Run
        ResponseEntity response =  controller.createReview(input);

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST, "Expected failed endpoint call with 400 status");
        Assert.notEmpty(((ReviewUploadPayload)response.getBody()).getMessages(), "Expected failed endpoint call with error messages");
    }
}
