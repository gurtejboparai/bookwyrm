package com.bookwyrm.backend.comment.validator;

import com.bookwyrm.backend.comment.input.CommentUploadInput;

import java.util.ArrayList;
import java.util.List;

public class CommentUploadValidator {
    public static List<String> validateUploadInformation(CommentUploadInput commentUploadInput) {
        List<String> errorList = new ArrayList<>();

        if (commentUploadInput.getCommentId() == null) {
            errorList.add("Comment ID is missing. PLease add the comment ID and try again.");
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
