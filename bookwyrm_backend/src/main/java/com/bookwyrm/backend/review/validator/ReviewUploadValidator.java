package com.bookwyrm.backend.review.validator;

import com.bookwyrm.backend.review.input.ReviewUploadInput;

import java.util.ArrayList;
import java.util.List;

public class ReviewUploadValidator {
    public static List<String> validateUploadInformation(ReviewUploadInput reviewUploadInput) {
        List<String> errorList = new ArrayList<>();

        if (reviewUploadInput.getAuthor() == null) {
            errorList.add("Author name missing. Please add an author name and try again.");
        }
        if (reviewUploadInput.getId() == null) {
            errorList.add("Review ID missing. Please add a review name and try again.");
        }
        if (reviewUploadInput.getDescription() == null) {
            errorList.add("Review description is missing. Please add a review description and try again.");
        }

        if (reviewUploadInput.getAnonymousFlag() == null) {
            errorList.add("Review anonymous flag is missing. Please let us know if this review is going to be anonymous or not and try again.");
        }

        return errorList;
    }
}
