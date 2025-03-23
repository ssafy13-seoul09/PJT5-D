package com.ssafy.review.model.service;

import java.util.List;

import com.ssafy.review.model.dto.Review;

/**
 * 영상 리뷰 관리를 위한 비즈니스 로직 인터페이스
 */
public interface ReviewService {

    public abstract Review select(int reviewId);

    public abstract List<Review> selectAll();

    public abstract boolean insertReview(Review review);

    public abstract boolean updateReview(Review review);

    public abstract boolean removeReview(int reviewId);

    public abstract boolean updateViewCnt(int reviewId);

    // 리뷰 조회 > youtubeId와 일치하는 리뷰 찾기 (video와 소통)
    public abstract List<Review> getReviewsbyId(String youtubeId);
}
