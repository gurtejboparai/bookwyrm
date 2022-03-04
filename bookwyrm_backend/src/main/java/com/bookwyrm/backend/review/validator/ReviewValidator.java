package com.bookwyrm.backend.review.validator;

import com.bookwyrm.backend.review.input.ReviewUploadInput;

import java.util.ArrayList;
import java.util.List;

public class ReviewValidator {
    public static List<String> validateUploadInformation(ReviewUploadInput reviewUploadInput) {
        List<String> errorList = new ArrayList<>();

        if (reviewUploadInput.getTitle() == null) {
            errorList.add("Review title is missing. Please add an title and try again.");
        }

        if (reviewUploadInput.getAuthor() == null) {
            errorList.add("Author name missing. Please add an author name and try again.");
        }
        if (reviewUploadInput.getBookId() == null) {
            errorList.add("Book ID missing. Please add a book Id and try again.");
        }
        if (reviewUploadInput.getContent() == null) {
            errorList.add("Review content is missing. Please add some content and try again.");
        }

        return errorList;
    }
}
