package com.bookwyrm.backend.review.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("review")
public class ReviewDao {

    @Id
    private String id;
    private String user;
    private String bookName;
    private String comment;

    public ReviewDao(String user, String bookName, String comment) {
        super();
        this.user = user;
        this.bookName = bookName;
        this.comment = comment;

    }

    public String getUser() {
        return user;
    }

    public String getBookName() {
        return bookName;
    }

    public String getComment() {
        return comment;
    }
}