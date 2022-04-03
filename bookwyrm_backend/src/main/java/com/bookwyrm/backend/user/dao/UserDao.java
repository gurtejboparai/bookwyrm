package com.bookwyrm.backend.user.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document("user")
public class UserDao {

    @Id
    private String username;
    private BigInteger passwordHash;
    private Boolean authorFlag;
    private String authorName;
    private Boolean journalistFlag;
    private String journalistName;

    public UserDao(String username, BigInteger passwordHash) {
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

    public Boolean getAuthorFlag() {
        return authorFlag;
    }

    public void setAuthorFlag(Boolean authorFlag) {
        this.authorFlag = authorFlag;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Boolean getJournalistFlag() {
        return journalistFlag;
    }

    public void setJournalistFlag(Boolean journalistFlag) {
        this.journalistFlag = journalistFlag;
    }

    public String getJournalistName() {
        return journalistName;
    }

    public void setJournalistName(String journalistName) {
        this.journalistName = journalistName;
    }
}