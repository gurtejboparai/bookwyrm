package com.bookwyrm.backend;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comment")
public class Comment {

    @Id
    private String id;
    private String user;
    private String bookName;
    private String comment;

    public Comment(String id, String user, String bookName, String comment) {
        this.id = id;
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