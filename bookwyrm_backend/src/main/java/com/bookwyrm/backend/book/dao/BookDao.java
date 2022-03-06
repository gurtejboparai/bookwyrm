package com.bookwyrm.backend.book.dao;

import com.bookwyrm.backend.review.dao.ReviewDao;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("book")
public class BookDao {

    @Id
    private String id;
    private String title;
    private List<ReviewDao> reviewList;
    private String author;

    public BookDao(String title, String author){
        super();
        this.title = title;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<ReviewDao> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewDao> reviewList) {
        this.reviewList = reviewList;
    }
}
