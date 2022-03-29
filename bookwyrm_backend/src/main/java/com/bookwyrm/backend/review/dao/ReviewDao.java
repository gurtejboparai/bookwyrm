package com.bookwyrm.backend.review.dao;

import com.bookwyrm.backend.comment.dao.CommentDao;
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
    private Boolean journalistReview;
    private List<CommentDao> commentList;
    private boolean anonymousFlag;
    private List<String> upVoteIdsList;
    private List<String> downVoteIdsList;
    private String journalistName;

    public ReviewDao(String user, boolean anonymousFlag, String content, String bookId, boolean journalistReview,
            String journalistName) {
        super();
        this.bookId = bookId;
        this.user = user;
        this.anonymousFlag = anonymousFlag;
        this.content = content;
        this.upVoteIdsList = new ArrayList<>();
        this.downVoteIdsList = new ArrayList<>();
        this.journalistReview = journalistReview;
        this.journalistName = journalistName;
    }

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

    public List<String> getUpVoteIdsList() {
        return upVoteIdsList;
    }

    public void setUpVoteIdsList(List<String> upVoteIdsList) {
        this.upVoteIdsList = upVoteIdsList;
    }

    public List<String> getDownVoteIdsList() {
        return downVoteIdsList;
    }

    public void setDownVoteIdsList(List<String> downVoteIdsList) {
        this.downVoteIdsList = downVoteIdsList;
    }

    public Boolean getJournalistReview() {
        return journalistReview;
    }

    public void setJournalistReview(Boolean journalistReview) {
        this.journalistReview = journalistReview;
    }

    public String getJournalistName() {
        return journalistName;
    }

    public void setJournalistName(String journalistName) {
        this.journalistName = journalistName;
    }
}