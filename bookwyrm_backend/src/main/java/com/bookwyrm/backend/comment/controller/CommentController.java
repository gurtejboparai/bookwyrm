package com.bookwyrm.backend.comment.controller;

import com.bookwyrm.backend.comment.dao.CommentService;
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

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentUploadPayload> createComment(
            @RequestBody CommentUploadInput commentUploadInput) {
        CommentUploadPayload response = new CommentUploadPayload();
        List<String> errorList = CommentValidator.validateUploadInformation(commentUploadInput);
        HttpStatus status = HttpStatus.OK;

        if (errorList.isEmpty()) {
            CommentDao comment = new CommentDao(
                    commentUploadInput.getAuthor(),
                    commentUploadInput.getContent(),
                    commentUploadInput.getAnonymousFlag(),
                    commentUploadInput.getReviewId());
            commentService.save(comment);

        } else {
            response.setMessages(errorList);
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(response);
    }
}
