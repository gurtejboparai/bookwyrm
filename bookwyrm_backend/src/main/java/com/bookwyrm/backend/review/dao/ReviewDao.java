package com.bookwyrm.backend.review.dao;

import com.bookwyrm.backend.comment.dao.CommentDao;
import com.bookwyrm.backend.review.genre.Genre;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document("review")
public class ReviewDao {

    @Id
    private String id;
    private String user;
    private String content;
    private String bookId;
    private List<CommentDao> commentList;
    private ArrayList<Genre> ratings;
    private boolean anonymousFlag;


    public ReviewDao(String user, boolean anonymousFlag, String content, String bookId, ArrayList<Genre> ratings) {
        super();
        this.bookId = bookId;
        this.user = user;
        this.anonymousFlag = anonymousFlag;
        this.content = content;
        this.ratings = ratings;
    }

    public ArrayList<Genre> getRatings() { return ratings;}

    public void setRatings(ArrayList<Genre> ratings) { this.ratings = ratings;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAnonymousFlag(boolean anonymousFlag) {
        this.anonymousFlag = anonymousFlag;
    }

    public String getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public boolean isAnonymousFlag() {
        return anonymousFlag;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public List<CommentDao> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentDao> commentList) {
        this.commentList = commentList;
    }
}