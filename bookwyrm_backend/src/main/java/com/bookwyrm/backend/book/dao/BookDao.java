package com.bookwyrm.backend.book.dao;

import com.bookwyrm.backend.review.dao.ReviewDao;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Document("book")
public class BookDao {

    @Id
    private String id;
    private String title;
    private List<ReviewDao> reviewList;
    private String description;
    private String author;
    private String isbn;
    private Map<String, Float> genre;


    public BookDao(String title, String author, String description, String isbn){
        super();
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
