package com.bookwyrm.backend.book.payload;

import java.util.List;

public class BookUploadPayload {
    private List<String> messages;
    private String bookName;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
