package com.bookwyrm.backend.review.payload;

import com.bookwyrm.backend.review.dao.ReviewDao;

import java.util.List;

public class ReviewUploadPayload {

    private List<String> messages;

    private ReviewDao reviewDao;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public ReviewDao getReviewDao() {
        return reviewDao;
    }

    public void setReviewDao(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }
}
