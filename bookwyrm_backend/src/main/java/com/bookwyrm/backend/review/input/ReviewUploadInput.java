package com.bookwyrm.backend.review.input;

public class ReviewUploadInput {

    private String bookId;
    private String author;
    private String content;
    private boolean journalistFlag;
    private String journalistName;

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
