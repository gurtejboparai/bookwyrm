package com.bookwyrm.backend.user.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserService extends MongoRepository<UserDao, String> {


}