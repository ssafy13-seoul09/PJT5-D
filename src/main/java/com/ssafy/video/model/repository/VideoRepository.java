package com.ssafy.video.model.repository;

import java.util.List;

import com.ssafy.review.model.dto.Review;
import com.ssafy.video.model.dto.Video;

public interface VideoRepository {
	public abstract List<Video> selectAll();
	
	public abstract Video select(String youtubeId);
	
	public abstract boolean updateViewCnt(String youtubeId);
	
	// youtubeId와 일치하는 리뷰 가져오기
	public abstract List<Review> getReviewbyId(String youtubeId);
	
}
