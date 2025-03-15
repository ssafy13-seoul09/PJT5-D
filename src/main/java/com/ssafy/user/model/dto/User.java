package com.ssafy.user.model.dto;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String password;
    private List<String> followings;
    private List<String> likedVideos;

    public User() {
    }

    public User(String userId, String password) {
        this.userId = userId; // 식별자
        this.password = password;
        this.followings = new ArrayList<String>();
        this.likedVideos = new ArrayList<String>();
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

    public List<String> getFollowings() {
        return followings;
    }

    public void setFollowings(List<String> followings) {
        this.followings = followings;
    }

    public List<String> getLikedVideos() {
        return likedVideos;
    }

    public void setLikedVideos(List<String> likedVideos) {
        this.likedVideos = likedVideos;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", password=" + password + ", followings=" + followings + ", likedVideos="
                + likedVideos + "]";
    }
}
