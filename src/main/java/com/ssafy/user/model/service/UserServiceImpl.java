package com.ssafy.user.model.service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.ssafy.user.model.dto.User;
import com.ssafy.video.model.dto.Video;
import com.ssafy.user.model.repository.UserRepository;
import com.ssafy.user.model.repository.UserRepositoryImpl;
import com.ssafy.util.ValidationUtils;

public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    private static final UserService INSTANCE = new UserServiceImpl();

    private UserServiceImpl() {
        repo = UserRepositoryImpl.getInstance();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean userIdExists(String userId) {
        return ValidationUtils.checkUserId(userId) && repo.checkUser(userId);
    }

    @Override
    public boolean authenticate(String userId, String password) {
        if (!ValidationUtils.checkUserId(userId) || password == null) {
            return false;
        }

        User user = repo.select(userId);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public boolean register(String userId, String password) {
        if (!ValidationUtils.checkUserId(userId) || password == null) {
            return false;
        }

        return !userIdExists(userId) && repo.insertUser(new User(userId, password));
    }

    @Override
    public User select(String userId) {
        return ValidationUtils.checkUserId(userId) ? repo.select(userId) : null;
    }

    @Override
    public List<String> getFollowings(String userId) {
        return ValidationUtils.checkUserId(userId) ? repo.getFollowings(userId) : new ArrayList<>();
    }

    @Override
    public boolean follow(String userId, String targetId) {
        if (!ValidationUtils.checkUserId(userId) || !ValidationUtils.checkUserId(targetId) || userId.equals(targetId)) {
            return false;
        }

        return repo.addFollowing(userId, targetId);
    }

    @Override
    public boolean unfollow(String userId, String targetId) {
        if (!ValidationUtils.checkUserId(userId) || !ValidationUtils.checkUserId(targetId) || userId.equals(targetId)) {
            return false;
        }

        return repo.removeFollowing(userId, targetId);
    }

    @Override
    public boolean checkFollowing(String userId, String targetId) {
        if (!ValidationUtils.checkUserId(userId) || !ValidationUtils.checkUserId(targetId) || userId.equals(targetId)) {
            return false;
        }

        return repo.checkFollowing(userId, targetId);
    }

    // 팔로잉 거리를 기준으로 새로 팔로우 할 유저 추천
    @Override
    public List<String> recommendUsers(String userId) {
        final int MAX_RECOMMENDATIONS = 10;
        // final int MAX_DEPTH = 3;

        List<String> followings = repo.getFollowings(userId);
        List<String> recommendedUsers = new ArrayList<>();

        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        visited.add(userId);
        for (String user : followings) {
            visited.add(user);
            q.add(user);
        }

        while (!q.isEmpty()) {
            String curr = q.poll();
            List<String> friends = repo.getFollowings(curr);

            for (String friend : friends) {
                if (!visited.contains(friend)) {
                    visited.add(friend);
                    q.add(friend);
                    if (recommendedUsers.size() < MAX_RECOMMENDATIONS) {
                        recommendedUsers.add(friend);
                    } else {
                        return recommendedUsers;
                    }
                }
            }
        }

        if (recommendedUsers.size() < MAX_RECOMMENDATIONS) {
            // TODO:  
        }

        return recommendedUsers;
    }

    @Override
    public List<Video> getLikedVideos(String userId) {
        return ValidationUtils.checkUserId(userId) ? repo.getLikedVideos(userId) : new ArrayList<>();
    }

    @Override
    public boolean likeVideo(String userId, String videoId) {
        return repo.addLikedVideo(userId, videoId);
    }

    @Override
    public boolean unlikeVideo(String userId, String videoId) {
        return repo.removeLikedVideo(userId, videoId);
    }

    @Override
    public boolean checkLikedVideo(String userId, String videoId) {
        return repo.checkLikedVideo(userId, videoId);
    }

}
