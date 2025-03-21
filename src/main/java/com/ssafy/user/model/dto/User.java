package com.ssafy.user.model.dto;

public class User {
    private String userId;
    private String password;

    public User() {
    }

    public User(String userId, String password) {
        this.userId = userId; // 식별자
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", password=" + password + "]";
    }
}
