package com.bookwyrm.backend.review.validator;

import com.bookwyrm.backend.review.input.ReviewUploadInput;
import com.bookwyrm.backend.review.input.ReviewVotingInput;

import java.util.ArrayList;
import java.util.List;

public class ReviewValidator {
    public static List<String> validateUploadInformation(ReviewUploadInput reviewUploadInput) {
        List<String> errorList = new ArrayList<>();

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

    public static List<String> validateVotingInput(ReviewVotingInput reviewVotingInput) {
        List<String> errorList = new ArrayList<>();

        if (reviewVotingInput.getUserId() == null) {
            errorList.add("User ID missing. Please try again.");
        }

        if (reviewVotingInput.getVoteValue() == null) {
            errorList.add("Vote value missing. Please down vote or upvote and try again.");
            System.out.println(reviewVotingInput.getVoteValue());
        }

        if (reviewVotingInput.getReviewId() == null) {
            errorList.add("review ID missing. Please try again.");
        }

        return errorList;
    }
}
