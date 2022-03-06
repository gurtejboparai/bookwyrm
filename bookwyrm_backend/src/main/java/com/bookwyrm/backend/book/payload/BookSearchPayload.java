package com.bookwyrm.backend.book.payload;

import com.bookwyrm.backend.book.dao.BookDao;

import java.util.List;

public class BookSearchPayload {
    List<BookDao> bookDaoList;
    List<String> messages;

    public List<BookDao> getBookDaoList() {
        return bookDaoList;
    }

    public void setBookDaoList(List<BookDao> bookDaoList) {
        this.bookDaoList = bookDaoList;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
