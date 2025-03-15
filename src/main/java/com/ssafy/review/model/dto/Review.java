package com.ssafy.review.model.dto;

import java.time.LocalDateTime;

// reviewcontroller: 개별 리뷰 조회, 생성, 수정

public class Review {
	private static int no = 1; // 누적시켜서 클래스변수로 저장, 고유 게시글번호++ 
	private int reviewId; // 고유 게시글 번호
	private String title; // 제목
	private String authorId; // 쓰니 > userId와 동일 
	private String contents; // 내용
	// 객체명 다르면 찾아보기
	private LocalDateTime createdAt;
	private int viewCnt;
	
	public Review() {
		// TODO Auto-generated constructor stub
	}
	
	// 입력받아야 하는 항목 3개
	public Review(String title, String authorId, String contents) {
		this.reviewId = no++;
		this.title = title;
		// authorId 지금 받는게 맞는건지?
		this.authorId = authorId;
		this.contents = contents;
		this.createdAt = LocalDateTime.now();
		// viewCnt 0으로 시작하는게 맞음?
		this.viewCnt = 0;
	}
	
	// getter, setter
	public static int getNo() {
		return no;
	}
	
	public static void setNo(int no) {
		Review.no = no;
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
