package com.ssafy.user.model.service;

import java.util.List;

import com.ssafy.user.model.dto.User;

public interface UserService {

    public abstract boolean userIdExists(String userId);

    public abstract boolean authenticate(String userId, String password);

    public abstract boolean register(String userId, String password);

    public abstract User select(String userId);

    public abstract List<String> getFollowings(String userId);

    public abstract boolean follow(String userId, String targetId);

    public abstract boolean unfollow(String userId, String targetId);

    public abstract List<String> getLikedVideos(String userId);

    public abstract boolean likeVideo(String userId, String videoId);

    public abstract boolean unlikeVideo(String userId, String videoId);
}
