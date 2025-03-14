package com.ssafy.user.model.service;

import java.util.List;

public interface UserService {

    public abstract boolean userIdExists(String userId);

    public abstract boolean isValid(String userId, String password);

    public abstract boolean register(String userId, String password);

    public abstract List<String> getFollowings(String userId);

    public abstract boolean follow(String userId, String targetId);

    public abstract boolean unfollow(String userId, String targetId);

    public abstract boolean like(String userId, String videoId);

    public abstract List<String> getLikedVideos(String userId);

    public abstract boolean unlike(String userId, String videoId);
}
