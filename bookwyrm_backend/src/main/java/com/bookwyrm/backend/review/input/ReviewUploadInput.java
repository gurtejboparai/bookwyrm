package com.bookwyrm.backend.review.input;

import java.util.Map;

public class ReviewUploadInput {

    private String bookId;
    private String author;
    private String content;
    private boolean journalistFlag;
    private String journalistName;
    private Map<String, Float> ratings;
    private boolean anonymousFlag;

    public Map<String, Float> getRatings() {return ratings;}

    public void setRatings(Map<String, Float> ratings) {this.ratings = ratings;}

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

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getAnonymousFlag() {
        return anonymousFlag;
    }

    public void setAnonymousFlag(boolean anonymousFlag) {
        this.anonymousFlag = anonymousFlag;
    }

    public boolean getJournalistFlag() {
        return journalistFlag;
    }

    public void setJournalistFlag(boolean journalistFlag) {
        this.journalistFlag = journalistFlag;
    }

    public String getJournalistName() {
        return journalistName;
    }

    public void setJournalistName(String journalistName) {
        this.journalistName = journalistName;
    }
}
