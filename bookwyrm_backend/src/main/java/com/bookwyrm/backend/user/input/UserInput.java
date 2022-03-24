package com.bookwyrm.backend.user.input;

import java.math.BigInteger;

public class UserInput {

    private String username;
    private BigInteger passwordHash;

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
