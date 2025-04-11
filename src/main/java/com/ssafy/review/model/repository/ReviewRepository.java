package com.ssafy.review.model.repository;

import java.util.List;

import com.ssafy.review.model.dto.Review;

public interface ReviewRepository {
    // 개별 리뷰 조회/삭제/업데이트 위한 1개 선택하기
    public abstract Review select(int reviewId);

    public abstract boolean insertReview(Review review);

    public abstract boolean updateReview(Review review);

    public abstract boolean deleteReview(int reviewId);

    // 사이트의 모든리뷰 가져오기
    public abstract List<Review> selectAll();

    // youtubeId와 일치하는 리뷰 찾아서 리턴 (videorepo와 소통)
    public abstract List<Review> getReviews(String youtubeId);

    public abstract boolean updateViewCnt(int reviewId);

}
