package com.ssafy.video.model.repository;

import java.util.List;

import com.ssafy.video.model.dto.Video;

public interface VideoRepository {
	public abstract List<Video> selectAll();
	
	public abstract Video select(String youtubeId);
	
	public abstract boolean updateViewCnt(String youtubeId);
	
}
