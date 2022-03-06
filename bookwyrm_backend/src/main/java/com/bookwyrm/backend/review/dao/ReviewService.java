package com.bookwyrm.backend.review.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ReviewService extends MongoRepository<ReviewDao, String> {

}