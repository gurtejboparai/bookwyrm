package com.bookwyrm.backend;

public interface CommentRepository extends MongoRepository<Comment, String> {

    @Query("{bookName: ?0}")
    Comment findCommentbyBook(String bookName);

    // public long count();
}