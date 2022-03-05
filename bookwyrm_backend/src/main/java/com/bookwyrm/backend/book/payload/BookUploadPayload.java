package com.bookwyrm.backend.book.payload;

import java.util.List;

public class BookUploadPayload {
    private List<String> messages;
    private String bookId;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
