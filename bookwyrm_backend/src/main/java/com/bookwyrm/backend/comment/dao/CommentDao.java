package com.bookwyrm.backend.comment.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("comment")
public class CommentDao {

    @Id
    private String id;
    private String content;
    private String author;
    private boolean anonymousFlag;

    public CommentDao(String content, String author, boolean anonymousFlag) {
        super();
        this.content = content;
        this.author = author;
        this.anonymousFlag = anonymousFlag;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public boolean isAnonymousFlag() {
        return anonymousFlag;
    }

}
