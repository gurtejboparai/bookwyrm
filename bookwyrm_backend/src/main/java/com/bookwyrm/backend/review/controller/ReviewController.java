package com.bookwyrm.backend.review.controller;

import com.bookwyrm.backend.review.input.ReviewUploadInput;
import com.bookwyrm.backend.review.payload.ReviewUploadPayload;
import com.bookwyrm.backend.review.validator.ReviewUploadValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    /*
     * Requires some tests:
     * Happy case (everything works as expected)
     * Missing Author
     * Missing Review ID
     * Missing Review Description
     * Missing Review Anonymous Flag
     * Missing Everything
     * */
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewUploadPayload> createReview(
            @RequestBody ReviewUploadInput reviewUploadInput,
            HttpServletRequest request) {
        ReviewUploadPayload response = new ReviewUploadPayload();
        List<String> errorList = ReviewUploadValidator.validateUploadInformation(reviewUploadInput);
        HttpStatus status = HttpStatus.OK;

        if (errorList.isEmpty()) {
            response.setReviewTitle(reviewUploadInput.getTitle());
            response.setReviewAuthor(reviewUploadInput.getAuthor());
            response.setReviewId(reviewUploadInput.getId());
            response.setReviewContent(reviewUploadInput.getContent());
            response.setReviewAnonymousFlag(reviewUploadInput.getAnonymousFlag());
        } else {
            response.setMessages(errorList);
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(response);
    }
}
