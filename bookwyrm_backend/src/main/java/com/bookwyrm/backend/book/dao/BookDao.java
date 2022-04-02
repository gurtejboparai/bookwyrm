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
    private Map<String, Float> avg;
    private Map<String, Integer> num;


    public BookDao(String title, String author, String description, String isbn){
        super();
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        avg = new HashMap<String, Float>();
        num = new HashMap<String, Integer>();
    }

    public void UpdateAvgRate(Map<String, Float> rate){
        for (Map.Entry m:rate.entrySet()){
            Object key = m.getKey();
            if (avg.containsKey(key)){
                int new_num = num.get(key) + 1;
                avg.replace(key.toString(),((avg.get(key) *num.get(key) + rate.get(key))/new_num));
                num.replace(key.toString(), new_num);
            }else{
                num.put(key.toString(), 1);
                avg.put(m.getKey().toString(), rate.get(key));
            }
        }
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

    public Map<String, Float> getAvg() {return avg;}

    public void setAvg(Map<String, Float> avg) {this.avg = avg;}
}
