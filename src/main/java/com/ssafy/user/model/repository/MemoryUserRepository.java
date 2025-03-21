package com.ssafy.user.model.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.user.model.dto.User;

public class MemoryUserRepository implements UserRepository {

    private static final UserRepository INSTANCE = new MemoryUserRepository();

    private Map<String, User> userMap = new HashMap<String, User>();

    private Map<String, List<String>> followingMap = new HashMap<String, List<String>>();
    private Map<String, List<String>> likedVideoMap = new HashMap<String, List<String>>();

    private MemoryUserRepository() {
        initializeDummyData();
    }

    private void initializeDummyData() {
        insertUser(new User("ssafy", "admin"));
        insertUser(new User("ssafy2", "admin"));
    }

    public static UserRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public User select(String userId) {
        return userMap.get(userId);
    }

    @Override
    public boolean insertUser(User user) {
        followingMap.put(user.getUserId(), new ArrayList<String>());
        likedVideoMap.put(user.getUserId(), new ArrayList<String>());
        return userMap.put(user.getUserId(), user) == null;
    }

    @Override
    public boolean updateUser(User user) {
        return userMap.put(user.getUserId(), user) != null;
    }

    @Override
    public boolean deleteUser(String userId) {
        followingMap.remove(userId);
        likedVideoMap.remove(userId);
        return userMap.remove(userId) != null;
    }

    @Override
    public boolean checkUser(String userId) {
        return userMap.containsKey(userId);
    }

    @Override
    public List<String> getFollowings(String userId) {
        return followingMap.get(userId);
    }

    @Override
    public boolean addFollowing(String userId, String targetId) {
        if (getFollowings(userId) == null || !userMap.containsKey(targetId)) {
            return false;
        }
        return followingMap.get(userId).add(targetId);
    }

    @Override
    public boolean removeFollowing(String userId, String targetId) {
        if (getFollowings(userId) == null || !userMap.containsKey(targetId)) {
            return false;
        }

        return followingMap.get(userId).remove(targetId);
    }

    @Override
    public List<String> getLikedVideos(String userId) {
        return likedVideoMap.get(userId);
    }

    @Override
    public boolean addLikedVideo(String userId, String videoId) {
        // TODO: Check if videoId exists
        if (getLikedVideos(userId) == null) {
            return false;
        }
        return likedVideoMap.get(userId).add(videoId);
    }

    @Override
    public boolean removeLikedVideo(String userId, String videoId) {
        if (getLikedVideos(userId) == null) {
            return false;
        }
        return likedVideoMap.get(userId).remove(videoId);
    }

}
