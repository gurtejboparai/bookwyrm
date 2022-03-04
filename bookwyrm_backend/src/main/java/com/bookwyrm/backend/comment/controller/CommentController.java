package com.bookwyrm.backend.comment.controller;

import com.bookwyrm.backend.comment.input.CommentUploadInput;
import com.bookwyrm.backend.comment.payload.CommentUploadPayload;
import com.bookwyrm.backend.comment.validator.CommentValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    /*
     * Requires some tests:
     * Happy case (everything works as expected)
     * Missing Author
     * Missing Comment Description
     * Missing Comment Anonymous Flag
     * Missing Everything
     * */
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentUploadPayload> createComment(
            @RequestBody CommentUploadInput commentUploadInput) {
        CommentUploadPayload response = new CommentUploadPayload();
        List<String> errorList = CommentValidator.validateUploadInformation(commentUploadInput);
        HttpStatus status = HttpStatus.OK;

        if (errorList.isEmpty()) {
            response.setCommentAuthor(commentUploadInput.getAuthor());
            response.setCommentDescription(commentUploadInput.getDescription());
            response.setCommentAnonymousFlag(commentUploadInput.getAnonymousFlag());
        } else {
            response.setMessages(errorList);
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(response);
    }
}
