package com.bookwyrm.backend.review.payload;

import java.util.List;

public class ReviewUploadPayload {

    private List<String> messages;
    private String reviewId;
    private String reviewDescription;
    private boolean reviewAnonymousFlag;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public boolean getReviewAnonymousFlag() {
        return reviewAnonymousFlag;
    }

    public void setReviewAnonymousFlag(boolean reviewAnonymousFlag) {
        this.reviewAnonymousFlag = reviewAnonymousFlag;
    }
}
