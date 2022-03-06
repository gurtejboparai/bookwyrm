package com.bookwyrm.backend.book.payload;

import com.bookwyrm.backend.book.dao.BookDao;

import java.util.List;

public class BookDetailSearchPayload {
    private BookDao bookDao;
    private List<String> messages;

    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
