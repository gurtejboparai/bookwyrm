package com.bookwyrm.backend.review.payload;

import java.util.List;

public class ReviewUploadPayload {

    private List<String> messages;
    private String reviewTitle;
    private String reviewAuthor;
    private String reviewId;
    private String reviewContent;
    private boolean reviewAnonymousFlag;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewAuthor() {
        return reviewAuthor;
    }

    public void setReviewAuthor(String reviewAuthor) {
        this.reviewAuthor = reviewAuthor;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public boolean getReviewAnonymousFlag() {
        return reviewAnonymousFlag;
    }

    public void setReviewAnonymousFlag(boolean reviewAnonymousFlag) {
        this.reviewAnonymousFlag = reviewAnonymousFlag;
    }
}
