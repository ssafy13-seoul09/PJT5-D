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
        final int NUM_RECOMMENDATIONS = 10;

        List<String> followings = repo.getFollowings(userId);
        List<String> recommendedUsers = new ArrayList<>();

        // BFS
        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        visited.add(userId);
        for (String followedUser : followings) {
            visited.add(followedUser);
            q.add(followedUser);
        }

        while (!q.isEmpty()) {
            String next = q.poll();
            List<String> nextFollowings = repo.getFollowings(next);

            for (String candidate : nextFollowings) {
                if (!visited.contains(candidate)) {
                    visited.add(candidate);
                    q.add(candidate);

                    recommendedUsers.add(candidate);
                    if (recommendedUsers.size() == NUM_RECOMMENDATIONS) {
                        return recommendedUsers;
                    }
                }
            }
        }

        // 추천 유저가 부족할경우 임의 유저 추천
        if (recommendedUsers.size() < NUM_RECOMMENDATIONS) {
            List<User> allUsers = repo.selectAll();
            for (User user : allUsers) {
                if (!visited.contains(user.getUserId())) {

                    recommendedUsers.add(user.getUserId());
                    if (recommendedUsers.size() == NUM_RECOMMENDATIONS) {
                        return recommendedUsers;
                    }
                }
            }
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
