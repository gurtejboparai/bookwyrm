package com.bookwyrm.backend.book.input;

import java.util.Map;
import java.util.HashMap;

public class BookUpdateInput {

    private String desc;
    private String id;
    private String genre;
    private Map<String, Float> ratings;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenre() { return genre;}

    public void setGenre(String genre) {this.genre = genre;}

    public Map<String, Float> getRate() {
        return ratings;
    }

    public void setRate(Map<String, Float> ratings) {
        this.ratings = ratings;
    }
}
