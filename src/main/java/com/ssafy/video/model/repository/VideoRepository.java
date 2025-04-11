package com.ssafy.video.model.repository;

import java.util.List;

import com.ssafy.review.model.dto.Review;
import com.ssafy.video.model.dto.Video;

/**
 * 비디오 정보 관리를 위한 데이터 접근 인터페이스
 */
public interface VideoRepository {

    public abstract Video select(String youtubeId);

    public abstract List<Video> selectAll();

    public abstract boolean updateViewCnt(String youtubeId);

    // youtubeId와 일치하는 리뷰 가져오기
    public abstract List<Review> getReviews(String youtubeId);

}
