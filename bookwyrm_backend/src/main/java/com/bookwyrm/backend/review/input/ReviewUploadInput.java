package com.bookwyrm.backend.review.input;

import com.bookwyrm.backend.review.genre.Genre;

import java.util.ArrayList;

public class ReviewUploadInput {

    private String bookId;
    private String author;
    private String content;



    private ArrayList<Genre> ratings;
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

    public ArrayList<Genre> getRatings() {return ratings;}

    public void setRatings(ArrayList<Genre> ratings) {this.ratings = ratings;}
    
    public boolean getAnonymousFlag() {
        return anonymousFlag;
    }

    public void setAnonymousFlag(boolean anonymousFlag) {
        this.anonymousFlag = anonymousFlag;
    }
}
