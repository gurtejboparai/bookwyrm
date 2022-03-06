package com.bookwyrm.backend.review.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("review")
public class ReviewDao {

    @Id
    private String id;
    private String user;
    private String content;
    private boolean anonymousFlag;

    public ReviewDao(String user, boolean anonymousFlag, String content) {
        super();
        this.user = user;
        this.anonymousFlag = anonymousFlag;
        this.content = content;

    }

    public String getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public boolean isAnonymousFlag() {
        return anonymousFlag;
    }
}