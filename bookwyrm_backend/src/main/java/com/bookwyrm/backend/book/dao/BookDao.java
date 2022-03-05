package com.bookwyrm.backend.book.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("book")
public class BookDao {

    @Id
    private String id;
    private String title;
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

}
