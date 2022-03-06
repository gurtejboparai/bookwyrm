package com.bookwyrm.backend.comment.dao;

import com.bookwyrm.backend.review.dao.ReviewDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CommentService extends MongoRepository<CommentDao, String> {

    @Query("{reviewId:'?0'}")
    List<CommentDao> getCommentsByReview(String reviewId);
}
