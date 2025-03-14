package com.ssafy.user.model.repository;

import com.ssafy.user.model.dto.User;

public interface UserRepository {
	public abstract boolean insertUser(User user);
	
	public abstract User select(String userId);
}
