package com.bookwyrm.backend.comment.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CommentService extends MongoRepository<CommentDao, String> {

}
