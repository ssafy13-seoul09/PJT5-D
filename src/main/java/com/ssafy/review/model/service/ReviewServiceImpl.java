package com.ssafy.review.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.review.model.dto.Review;
import com.ssafy.review.model.repository.ReviewRepository;
import com.ssafy.review.model.repository.ReviewRepositoryImpl;
import com.ssafy.util.ValidationUtils;

public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repo;

    private static final ReviewService INSTANCE = new ReviewServiceImpl();

    public ReviewServiceImpl() {
        repo = ReviewRepositoryImpl.getInstance();
    }

    public static ReviewService getInstance() {
        return INSTANCE;
    }

    @Override
    public Review select(int reviewId) {
        return ValidationUtils.checkReviewId(reviewId) ? repo.select(reviewId) : null;
    }

    @Override
    public List<Review> selectAll() {
        return repo.selectAll();
    }

    @Override
    public boolean insertReview(Review review) {
        // TODO: 리뷰 제목 및 내용 유효성 검사
        if (review == null
                || !ValidationUtils.checkYoutubeId(review.getYoutubeId())
                || !ValidationUtils.checkUserId(review.getAuthorId())) {
            return false;
        }

        return repo.insertReview(review);
    }

    @Override
    public List<Review> getReviewsbyId(String youtubeId) {
        return ValidationUtils.checkYoutubeId(youtubeId) ? repo.getReviews(youtubeId) : new ArrayList<>();
    }

    @Override
    public boolean updateReview(Review review) {
        // TODO: 리뷰 제목 및 내용 유효성 검사
        if (review == null
                || !ValidationUtils.checkYoutubeId(review.getYoutubeId())
                || !ValidationUtils.checkUserId(review.getAuthorId())
                || !ValidationUtils.checkReviewId(review.getReviewId())) {
            return false;
        }

        return repo.updateReview(review);
    }

    @Override
    public boolean removeReview(int reviewId) {
        return ValidationUtils.checkReviewId(reviewId) && repo.deleteReview(reviewId);
    }

    @Override
    public boolean updateViewCnt(int reviewId) {
        return ValidationUtils.checkReviewId(reviewId) && repo.updateViewCnt(reviewId);
    }
}
