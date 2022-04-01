package com.bookwyrm.backend.review.controller;

import com.bookwyrm.backend.book.dao.BookDao;
import com.bookwyrm.backend.book.service.BookService;
import com.bookwyrm.backend.review.dao.ReviewDao;
import com.bookwyrm.backend.review.dao.ReviewService;
import com.bookwyrm.backend.review.input.ReviewUploadInput;
import com.bookwyrm.backend.review.input.ReviewVotingInput;
import com.bookwyrm.backend.review.payload.ReviewUploadPayload;
import com.bookwyrm.backend.review.validator.ReviewValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    BookService bookService;
    @Autowired
    ReviewService reviewService;

    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewUploadPayload> createReview(
            @RequestBody ReviewUploadInput reviewUploadInput) {
        ReviewUploadPayload response = new ReviewUploadPayload();

        List<String> errorList = ReviewValidator.validateUploadInformation(reviewUploadInput);
        HttpStatus status = HttpStatus.OK;

        if (errorList.isEmpty()) {
            ReviewDao reviewDao = new ReviewDao(
                    (!reviewUploadInput.getAnonymousFlag()) ? reviewUploadInput.getAuthor() : "Anonymous",
                    reviewUploadInput.getAnonymousFlag(),
                    reviewUploadInput.getContent(),
                    reviewUploadInput.getBookId(),
                    reviewUploadInput.getRatings(),
                    reviewUploadInput.getJournalistFlag(),
                    reviewUploadInput.getJournalistName()
                    );
            reviewService.save(reviewDao);

        } else {
            response.setMessages(errorList);
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(response);
    }

    @CrossOrigin
    @PutMapping(value = "/voting",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewUploadPayload> updateVoting(
            @RequestBody ReviewVotingInput reviewVotingInput) {
        ReviewDao foundReview = reviewService.findById(reviewVotingInput.getReviewId()).get();
        HttpStatus status = HttpStatus.OK;
        ReviewUploadPayload payload = new ReviewUploadPayload();
        List<String> errorList = ReviewValidator.validateVotingInput(reviewVotingInput);

        if (errorList.isEmpty()) {
            if ((reviewVotingInput.getVoteValue() == false) && !(foundReview.getDownVoteIdsList().contains(reviewVotingInput.getUserId()))) {
                foundReview.getDownVoteIdsList().add(reviewVotingInput.getUserId());
                foundReview.getUpVoteIdsList().remove(reviewVotingInput.getUserId());
            }
            if ((reviewVotingInput.getVoteValue() == true) && !(foundReview.getUpVoteIdsList().contains(reviewVotingInput.getUserId()))) {
                foundReview.getUpVoteIdsList().add(reviewVotingInput.getUserId());
                foundReview.getDownVoteIdsList().remove(reviewVotingInput.getUserId());
            }
            //Save review
            reviewService.save(foundReview);
            payload.setReviewDao(foundReview);

        }else {
            payload.setMessages(errorList);
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(payload);
    }
}
