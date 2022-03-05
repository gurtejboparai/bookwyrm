package com.bookwyrm.backend.comment.validator;

import com.bookwyrm.backend.comment.input.CommentUploadInput;

import java.util.ArrayList;
import java.util.List;

public class CommentValidator {
    public static List<String> validateUploadInformation(CommentUploadInput commentUploadInput) {
        List<String> errorList = new ArrayList<>();

        if (commentUploadInput.getReviewId() == null) {
            errorList.add("Review ID is missing. PLease add the review ID and try again.");
        }
        if (commentUploadInput.getAuthor() == null) {
            errorList.add("Author name missing. Please add an author name and try again.");
        }
        if (commentUploadInput.getContent() == null) {
            errorList.add("Comment content is missing. Please add something as content and try again.");
        }

        return errorList;
    }
}
