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
    private String genre;
    private Map<String, Float> avgRate;
    private Map<String, Integer> numRate;


    public BookDao(String title, String author, String description, String isbn){
        super();
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        avgRate = new HashMap<String, Float>();
        numRate = new HashMap<String, Integer>();
    }

    public void UpdateAvgRate(Map<String, Float> rate){
        for (Map.Entry m:rate.entrySet()){
            Object key = m.getKey();
            if (rate.get(key) != 0) {
                if (avgRate.containsKey(key)) {
                    int new_num = numRate.get(key) + 1;
                    Float result = ((avgRate.get(key) * numRate.get(key) + rate.get(key)) / new_num);
                    result = ((int) (result * 2 + 0.5)) / 2.0F;
                    avgRate.replace(key.toString(), result);
                    numRate.replace(key.toString(), new_num);
                } else {
                    numRate.put(key.toString(), 1);
                    avgRate.put(m.getKey().toString(), rate.get(key));
                }
            }
        }
    }
    public void setGenre(String genre) { this.genre = genre;}
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

    public Map<String, Float> getAvg() {return avgRate;}

    public void setAvg(Map<String, Float> avg) {this.avgRate = avg;}
}
