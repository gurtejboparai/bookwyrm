package com.bookwyrm.backend.review.input;

import java.util.Map;

public class ReviewUploadInput {

    private String bookId;
    private String author;
    private String content;
    private Map<String, Float> ratings;
    private boolean anonymousFlag;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {this.content = content;}

    public Map<String, Float> getRatings() {return ratings;}

    public void setRatings(Map<String, Float> ratings) { this.ratings = ratings;}
    
    public boolean getAnonymousFlag() {
        return anonymousFlag;
    }

    public void setAnonymousFlag(boolean anonymousFlag) {
        this.anonymousFlag = anonymousFlag;
    }
}
