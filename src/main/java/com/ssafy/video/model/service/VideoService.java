package com.ssafy.video.model.service;
import java.util.List;

import com.ssafy.video.model.dto.Video;

public interface VideoService {
	// video 
	// 전체 비디오를 선택한다.
	public abstract List<Video> selectAll();
	
	// 조회수 가장 많은 비디오
	public abstract List<Video> selectPopularVideos();
	
	// 부위별로 비디오를 선택한다.
	public abstract List<Video> selectBodypartVideos(String fitBodyPart);
	
	// 비디오 하나를 선택한다.
	public abstract Video selectOne(String youtubeId);	
	
	
}
