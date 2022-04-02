package com.bookwyrm.backend.comment.controller;

import com.bookwyrm.backend.comment.dao.CommentService;
import com.bookwyrm.backend.comment.input.CommentUploadInput;
import com.bookwyrm.backend.comment.validator.CommentValidator;
import com.bookwyrm.backend.comment.dao.CommentDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> createComment(
            @RequestBody CommentUploadInput commentUploadInput) {
        List<String> response = new ArrayList<>();
        List<String> errorList = CommentValidator.validateUploadInformation(commentUploadInput);
        HttpStatus status = HttpStatus.OK;

        if (errorList.isEmpty()) {
            CommentDao comment = new CommentDao(
                    commentUploadInput.getContent(),
                    (!commentUploadInput.getAnonymousFlag()) ? commentUploadInput.getAuthor() : "Anonymous",
                    commentUploadInput.getAnonymousFlag(),
                    commentUploadInput.getReviewId());
            commentService.save(comment);

        } else {
            response = errorList;
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(response);
    }
}
