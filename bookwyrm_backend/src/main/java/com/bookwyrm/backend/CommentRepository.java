package com.bookwyrm.backend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CommentRepository extends MongoRepository<Comment, String> {

    @Query("{bookName: ?0}")
    List<Comment> findCommentbyBook(String bookName);

    // public long count();
}