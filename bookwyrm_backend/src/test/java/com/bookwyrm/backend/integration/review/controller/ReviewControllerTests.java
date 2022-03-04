package com.bookwyrm.backend.integration.review.controller;

import com.bookwyrm.backend.review.controller.ReviewController;
import com.bookwyrm.backend.review.input.ReviewUploadInput;
import com.bookwyrm.backend.review.payload.ReviewUploadPayload;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;

@SpringJUnitConfig
public class ReviewControllerTests {

    @Test
    public void testHappyPath(){
        //Setup
        ReviewUploadInput input = new ReviewUploadInput();
        input.setAuthor("testAuthor");
        input.setAnonymousFlag(true);
        input.setDescription("testContent");
        input.setId("testId");

        //Run
        ResponseEntity response =  (new ReviewController()).createReview(input);

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.OK, "Expected successful endpoint call with 200 status");
        Assert.isNull(((ReviewUploadPayload)response.getBody()).getMessages() , "Expected successful endpoint call with no error messages");

    }
    @Test
    public void testBadRequest(){
        //Setup
        ReviewUploadInput input = new ReviewUploadInput();

        //Run
        ResponseEntity response =  (new ReviewController()).createReview(input);

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST, "Expected failed endpoint call with 400 status");
        Assert.notEmpty(((ReviewUploadPayload)response.getBody()).getMessages(), "Expected failed endpoint call with error messages");
    }
}
