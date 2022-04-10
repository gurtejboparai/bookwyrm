package com.bookwyrm.backend.book.service;

import com.bookwyrm.backend.book.dao.BookDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookService extends MongoRepository<BookDao,String> {

    @Query("{title:{$regex:'(?i)?0'}}")
    List<BookDao> findAllBooksWithTitle(String title);

    @Query("{id:'?0'}")
    BookDao findByBookId(String id);

}
