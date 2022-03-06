package com.bookwyrm.backend.book.dao;

import com.bookwyrm.backend.review.dao.ReviewDao;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("book")
public class BookDao {

    @Id
    private String id;
    private String title;
    private String author;
    private List<ReviewDao> reviewList;

    public BookDao(String title, String author){
        super();
        this.reviewList = new ArrayList<>();
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

    public void pushReview(ReviewDao newReview){
        reviewList.add(newReview);
    }

}
