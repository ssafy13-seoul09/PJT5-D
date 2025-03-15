package com.ssafy.review.model.repository;

import java.util.List;

import com.ssafy.review.model.dto.Review;

public interface ReviewRepository {
	// 사이트의 모든리뷰 가져오기
	public abstract List<Review> selectAll();
	
	// 개별리뷰 -> 비디오에 연결
	public abstract Review select(int reviewId);
	
	public abstract boolean insertReview(Review review);
	
	public abstract boolean updateReview(Review review);
	
	public abstract boolean deleteReview(int reviewId); 
	
	public abstract boolean updateViewCnt(int reviewId);
	
}
