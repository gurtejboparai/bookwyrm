package com.bookwyrm.backend.user.payload;

import com.bookwyrm.backend.user.dao.UserDao;

import java.util.List;

public class UserPayload {
    UserDao user;
    List<String> errorList;

    public UserDao getUser() {
        return user;
    }

    public void setUser(UserDao user) {
        this.user = user;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}
