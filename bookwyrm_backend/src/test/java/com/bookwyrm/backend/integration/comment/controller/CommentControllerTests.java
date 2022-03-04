package com.bookwyrm.backend.integration.comment.controller;

import com.bookwyrm.backend.comment.controller.CommentController;
import com.bookwyrm.backend.comment.input.CommentUploadInput;
import com.bookwyrm.backend.comment.payload.CommentUploadPayload;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;

@SpringJUnitConfig
public class CommentControllerTests {

    @Test
    public void testHappyPath(){
        //Setup
        CommentUploadInput input = new CommentUploadInput();
        input.setAuthor("testAuthor");
        input.setAnonymousFlag(true);
        input.setContent("testContent");
        input.setReviewId("testReviewId");

        //Run
        ResponseEntity response =  (new CommentController()).createComment(input);

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.OK, "Expected successful endpoint call with 200 status");
        Assert.isNull(((CommentUploadPayload)response.getBody()).getMessages() , "Expected successful endpoint call with no error messages");

    }
    @Test
    public void testBadRequest(){
        //Setup
        CommentUploadInput input = new CommentUploadInput();

        //Run
        ResponseEntity response =  (new CommentController()).createComment(input);

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST, "Expected failed endpoint call with 400 status");
        Assert.notEmpty(((CommentUploadPayload)response.getBody()).getMessages(), "Expected failed endpoint call with error messages");
    }
}