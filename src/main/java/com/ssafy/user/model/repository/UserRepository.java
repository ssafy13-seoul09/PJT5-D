package com.ssafy.user.model.repository;

import java.util.List;

import com.ssafy.user.model.dto.User;

public interface UserRepository {

    // Core
    public abstract User select(String userId);

    public abstract boolean insertUser(User user);

    public abstract boolean updateUser(User user);

    public abstract boolean deleteUser(String userId);

    public abstract boolean checkUser(String userId);

    // Relational
    public abstract List<String> getFollowings(String userId);

    public abstract boolean addFollowing(String userId, String targetId);

    public abstract boolean removeFollowing(String userId, String targetId);

    public abstract List<String> getLikedVideos(String userId);

    public abstract boolean addLikedVideo(String userId, String videoId);

    public abstract boolean removeLikedVideo(String userId, String videoId);
}
