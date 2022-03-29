package com.bookwyrm.backend.user.input;

import java.math.BigInteger;

public class UserUpdateInput {

    private String username;
    private Boolean authorFlag;
    private String authorName;
    private Boolean profJournalistFlag;
    private String profJournalistName;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Boolean getProfJournalistFlag() {
        return profJournalistFlag;
    }

    public void setProfJournalistFlag(Boolean profJournalistFlag) {
        this.profJournalistFlag = profJournalistFlag;
    }

    public String getProfJournalistName() {
        return profJournalistName;
    }

    public void setProfJournalistName(String profJournalistName) {
        this.profJournalistName = profJournalistName;
    }
}
