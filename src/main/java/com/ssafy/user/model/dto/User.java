package com.ssafy.user.model.dto;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String userId;
	private String password;
	// video, user 각 id로 
	private List<String> followings;
	private List<String> likedVideos;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String userId, String password, List<String> followings, List<String> likedVideos) {
		// TODO Auto-generated constructor stub
		this.userId = userId; // 식별자
		this.password = password;

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getFollowings() {
		return followings;
	}

	public void setFollowings(List<String> followings) {
		this.followings = followings;
	}

	public List<String> getLikedVideos() {
		return likedVideos;
	}

	public void setLikedVideos(List<String> likedVideos) {
		this.likedVideos = likedVideos;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
}
