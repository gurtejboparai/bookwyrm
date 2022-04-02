package com.bookwyrm.backend.book.input;

import java.util.Map;
import java.util.HashMap;

public class BookUpdateInput {

    private String desc;
    private String id;
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

    public Map<String, Float> getRate() {
        return ratings;
    }

    public void setRate(Map<String, Float> ratings) {
        this.ratings = ratings;
    }
}
