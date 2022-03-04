package com.bookwyrm.backend.integration.comment.controller;

import com.bookwyrm.backend.book.controller.BookController;
import com.bookwyrm.backend.book.input.BookUploadInput;
import com.bookwyrm.backend.book.payload.BookUploadPayload;
import com.bookwyrm.backend.comment.controller.CommentController;
import com.bookwyrm.backend.comment.input.CommentUploadInput;
import com.bookwyrm.backend.comment.payload.CommentUploadPayload;
import com.bookwyrm.backend.comment.validator.CommentUploadValidator;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SpringJUnitConfig
public class CommentControllerTests {

    @Test
    public void testHappyPath(){
        //Setup
        CommentUploadInput input = new CommentUploadInput();
        input.setAuthor("testAuthor");
        input.setAnonymousFlag(true);
        input.setDescription("testContent");

        //Run
        CommentController controller = new CommentController();
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
        CommentController controller = new CommentController();
        ResponseEntity response =  controller.createComment(input);

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST, "Expected failed endpoint call with 400 status");
        Assert.notEmpty(((CommentUploadPayload)response.getBody()).getMessages(), "Expected failed endpoint call with error messages");
    }
}