package com.bookwyrm.backend.comment.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("comment")
public class CommentDao {

    @Id
    private String id;
    private String content;
    private String reviewId;
    private String author;
    private boolean anonymousFlag;

    public CommentDao(String content, String author, boolean anonymousFlag, String reviewId) {
        super();
        this.reviewId = reviewId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAnonymousFlag(boolean anonymousFlag) {
        this.anonymousFlag = anonymousFlag;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }
}
