package com.ssafy.user.model.service;

import java.util.List;

import com.ssafy.user.model.dto.User;
import com.ssafy.user.model.repository.MemoryUserRepository;
import com.ssafy.user.model.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

public class UserServiceImpl implements UserService {

    private static UserService service = new UserServiceImpl();
    private UserRepository repo = MemoryUserRepository.getInstance();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        return service;
    }

    @Override
    public boolean isValid(String userId, String password) {
        if (repo.select(userId) == null) {
            return false;
        }

        if (!repo.select(userId).getPassword().equals(password)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean register(String userId, String password) {
        if (repo.select(userId) != null) {
            return false;
        }

        User user = new User(userId, password);
        repo.insertUser(user);
        return true;
    }

    @Override
    public boolean follow(String userId, String targetId) {
        User user = repo.select(userId);
        if (user == null) {
            return false;
        }

        User target = repo.select(targetId);
        if (target == null) {
            return false;
        }

        if (user.getFollowings().contains(targetId)) {
            return false;
        }

        return user.getFollowings().add(targetId);
    }

    @Override
    public boolean unfollow(String userId, String targetId) {
        User user = repo.select(userId);
        if (user == null) {
            return false;
        }

        User target = repo.select(targetId);
        if (target == null) {
            return false;
        }

        return user.getFollowings().remove(targetId);
    }

    @Override
    public boolean like(String userId, String videoId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'like'");
    }

    @Override
    public boolean unlike(String userId, String videoId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unlike'");
    }

    @Override
    public boolean userIdExists(String userId) {
        return repo.select(userId) != null;
    }

	@Override
	public List<String> getFollowings(String userId) {
        return repo.select(userId).getFollowings();
	}

	@Override
	public List<String> getLikedVideos(String userId) {
        return repo.select(userId).getLikedVideos();
	}
}
