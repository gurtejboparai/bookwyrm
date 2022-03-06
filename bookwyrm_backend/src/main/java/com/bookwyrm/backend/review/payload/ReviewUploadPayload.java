package com.bookwyrm.backend.review.payload;

import java.util.List;

public class ReviewUploadPayload {

    private List<String> messages;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

}
