package com.bookwyrm.backend.book.service;

import com.bookwyrm.backend.book.dao.BookDao;
import java.util.List;

public interface SearchService {

    public List<BookDao> tokenSearch(String title);

    public List<BookDao> originalSearch(String title);

}
