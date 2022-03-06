package com.bookwyrm.backend.review.dao;

import com.bookwyrm.backend.comment.dao.CommentDao;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document("review")
public class ReviewDao {

    @Id
    private String id;
    private String bookId;
    private String user;
    private String content;
    List<CommentDao> comments;
    private boolean anonymousFlag;

    public ReviewDao(String bookId, String user, boolean anonymousFlag, String content) {
        super();
        this.bookId = bookId;
        this.user = user;
        this.anonymousFlag = anonymousFlag;
        this.content = content;
        comments = new ArrayList<>();
    }

    public void addComment(CommentDao comment) {
        comments.add(comment);
    }

    public List<CommentDao> getCommentList() {
        return comments;
    }

    public void addCommentList(List<CommentDao> list) {
        System.arraycopy(list, 0, comments, 0, list.size());
    }

    public String getBookId() {
        return bookId;
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
}