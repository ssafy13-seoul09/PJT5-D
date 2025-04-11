package com.ssafy.review.model.dto;

import java.sql.Timestamp;

/**
 * 영상 리뷰 데이터 전송을 위한 DTO
 *
 * - 리뷰는 reviewId로 고유하게 식별됨
 * - 작성자는 회원 ID와 동일
 */
public class Review {
    private int reviewId;
    private String youtubeId;
    private String authorId;
    private String title;
    private String contents;
    private Timestamp createdAt;
    private int viewCnt;

    public Review() {
    }

    public Review(int reviewId, String youtubeId, String authorId, String title, String contents, Timestamp createdAt,
            int viewCnt) {
        this.reviewId = reviewId;
        this.youtubeId = youtubeId;
        this.authorId = authorId;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.viewCnt = viewCnt;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    @Override
    public String toString() {
        return "Review [reviewId=" + reviewId + ", title=" + title + ", authorId=" + authorId + ", contents=" + contents
                + ", createdAt=" + createdAt + ", viewCnt=" + viewCnt + ", youtubeId=" + youtubeId + "]";
    }
}
