package com.bookwyrm.backend.comment.controller;

import com.bookwyrm.backend.comment.input.CommentUploadInput;
import com.bookwyrm.backend.comment.payload.CommentUploadPayload;
import com.bookwyrm.backend.comment.validator.CommentValidator;
import com.bookwyrm.backend.review.dao.ReviewService;
import com.bookwyrm.backend.review.dao.ReviewDao;
import com.bookwyrm.backend.comment.dao.CommentDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    ReviewService reviewService;

    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentUploadPayload> createComment(
            @RequestBody CommentUploadInput commentUploadInput) {
        CommentUploadPayload response = new CommentUploadPayload();
        List<String> errorList = CommentValidator.validateUploadInformation(commentUploadInput);
        HttpStatus status = HttpStatus.OK;

        if (errorList.isEmpty()) {
            CommentDao comment = new CommentDao(commentUploadInput.getAuthor(),
                    commentUploadInput.getContent(),
                    commentUploadInput.getAnonymousFlag());
            Optional<ReviewDao> review = reviewService.findById(commentUploadInput.getReviewId());
            ReviewDao review1 = new ReviewDao(review.get().getBookId(), review.get().getUser(),
                    review.get().isAnonymousFlag(), review.get().getContent());
            review1.addCommentList(review.get().getCommentList());
            review1.addComment(comment);
            reviewService.save(review1);
            reviewService.delete(review.get());
        } else {
            response.setMessages(errorList);
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(response);
    }
}
