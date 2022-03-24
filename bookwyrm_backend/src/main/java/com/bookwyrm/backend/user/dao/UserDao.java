package com.bookwyrm.backend.user.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document("user")
public class UserDao {

    @Id
    private String username;
    private BigInteger passwordHash;

    public UserDao( String username, BigInteger passwordHash) {
        super();
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigInteger getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(BigInteger passwordHash) {
        this.passwordHash = passwordHash;
    }
}