package com.bookwyrm.backend.review.validator;

import com.bookwyrm.backend.review.input.ReviewUploadInput;

import java.util.ArrayList;
import java.util.List;

public class ReviewValidator {
    public static List<String> validateUploadInformation(ReviewUploadInput reviewUploadInput) {
        List<String> errorList = new ArrayList<>();

        if (reviewUploadInput.getAuthor() == null) {
            errorList.add("Author name missing. Please add an author name and try again.");
        }
        if (reviewUploadInput.getId() == null) {
            errorList.add("Book ID missing. Please add a book Id and try again.");
        }
        if (reviewUploadInput.getDescription() == null) {
            errorList.add("Review description is missing. Please add a review description and try again.");
        }

        return errorList;
    }
}
