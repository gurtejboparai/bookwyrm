package com.bookwyrm.backend;

@Document("comment")
public class Comment {

    private String user;
    private String bookName;
    private String comment;

    public Comment(String id, String user, String bookName, String comment) {
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