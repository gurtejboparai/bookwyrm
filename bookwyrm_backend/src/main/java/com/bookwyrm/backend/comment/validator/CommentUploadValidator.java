package com.bookwyrm.backend.comment.validator;

import com.bookwyrm.backend.comment.input.CommentUploadInput;

import java.util.ArrayList;
import java.util.List;

public class CommentUploadValidator {
    public static List<String> validateUploadInformation(CommentUploadInput commentUploadInput) {
        List<String> errorList = new ArrayList<>();

        if (commentUploadInput.getAuthor() == null) {
            errorList.add("Author name missing. Please add an author name and try again.");
        }
        if (commentUploadInput.getDescription() == null) {
            errorList.add("Comment description is missing. Please add a comment description and try again.");
        }

        return errorList;
    }
}
