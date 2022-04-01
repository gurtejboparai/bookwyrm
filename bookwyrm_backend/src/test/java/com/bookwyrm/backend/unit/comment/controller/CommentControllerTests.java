package com.bookwyrm.backend.unit.comment.controller;

import com.bookwyrm.backend.book.controller.BookController;
import com.bookwyrm.backend.book.service.BookService;
import com.bookwyrm.backend.comment.controller.CommentController;
import com.bookwyrm.backend.comment.dao.CommentService;
import com.bookwyrm.backend.comment.input.CommentUploadInput;
import com.bookwyrm.backend.comment.payload.CommentUploadPayload;
import com.bookwyrm.backend.review.dao.ReviewDao;
import com.bookwyrm.backend.review.dao.ReviewService;
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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringJUnitConfig
@SpringBootTest
public class CommentControllerTests {

    @InjectMocks
    private CommentController controller;

    @Mock
    private ReviewService reviewService;

    @Mock
    CommentService commentService;

    @Test
    public void testHappyPath(){
        MockitoAnnotations.openMocks(this);
        Mockito.when(reviewService.findById(any(String.class))).thenReturn(Optional.of(new ReviewDao("testauthor",
                false, "test content", "bookId", new HashMap<String, Float>())));
        //Setup
        CommentUploadInput input = new CommentUploadInput();
        input.setAuthor("testAuthor");
        input.setAnonymousFlag(true);
        input.setContent("testContent");
        input.setReviewId("testReviewId");

        //Run
        ResponseEntity response =  controller.createComment(input);

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.OK, "Expected successful endpoint call with 200 status");
        Assert.isNull(((CommentUploadPayload)response.getBody()).getMessages() , "Expected successful endpoint call with no error messages");

    }
    @Test
    public void testBadRequest(){
        //Setup
        CommentUploadInput input = new CommentUploadInput();

        //Run
        ResponseEntity response =  controller.createComment(input);

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST, "Expected failed endpoint call with 400 status");
        Assert.notEmpty(((CommentUploadPayload)response.getBody()).getMessages(), "Expected failed endpoint call with error messages");
    }
}