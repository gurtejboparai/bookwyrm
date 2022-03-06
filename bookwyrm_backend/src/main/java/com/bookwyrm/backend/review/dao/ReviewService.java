package com.bookwyrm.backend.review.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReviewService extends MongoRepository<ReviewDao, String> {

    @Query("{bookId:'?0'}")
    List<ReviewDao> getReviewsByBook(String bookId);

}