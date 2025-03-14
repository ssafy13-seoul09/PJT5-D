package com.ssafy.review.model.dto;

import java.time.LocalDateTime;

// reviewcontroller: 개별 리뷰 조회, 생성, 수정

public class Review {
	private int reviewId; //연번 
	private String title;
	private String authorId;
	private String contents;
	// 객체명 다르면 찾아보기
	private LocalDateTime createdAt;
	private int viewCnt;
	
	public Review() {
		// TODO Auto-generated constructor stub
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	
}
