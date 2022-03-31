package com.bookwyrm.backend.user.input;

public class UserUpdateInput {

    private String username;
    private Boolean authorFlag;
    private String authorName;
    private Boolean journalistFlag;
    private String journalistName;


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
