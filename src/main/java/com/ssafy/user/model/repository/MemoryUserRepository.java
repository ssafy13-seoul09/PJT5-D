package com.ssafy.user.model.repository;

import java.util.HashMap;
import java.util.Map;

import com.ssafy.user.model.dto.User;

public class MemoryUserRepository implements UserRepository {

    private static UserRepository repo = new MemoryUserRepository();
    private Map<String, User> userMap = new HashMap<String, User>();

    private MemoryUserRepository() {
        insertDummy();
    }

    private void insertDummy() {
        User admin = new User("ssafy", "admin");
        userMap.put(admin.getUserId(), admin);
    }

    public static UserRepository getInstance() {
        return repo;
    }

    @Override
    public User select(String userId) {
        return userMap.get(userId);
    }

    @Override
    public boolean insertUser(User user) {
        if (userMap.containsKey(user.getUserId())) {
            return false;
        }

        userMap.put(user.getUserId(), user);
        return true;
    }
}
