package com.bookwyrm.backend.review.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ReviewService extends MongoRepository<ReviewDao, String> {

    @Query("{bookName: ?0}")
    List<ReviewDao> findCommentbyBook(String bookName);

}