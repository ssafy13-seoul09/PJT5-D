package com.ssafy.user.model.service;

public interface UserService {

    public abstract boolean isValid(String userId, String password);

    public abstract boolean register(String userId, String password);

    public abstract boolean follow(String userId, String targetId);

    public abstract boolean unfollow(String userId, String targetId);

    public abstract boolean like(String userId, String videoId);

    public abstract boolean unlike(String userId, String videoId);
}
