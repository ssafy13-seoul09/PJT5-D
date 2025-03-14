// loginuser > session단위로 저장
// act OOO action XXX 
// repo > db친화, 조회(전체/개별), 수정, 삭제
package com.ssafy.review.model.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.review.model.dto.Review;

public class ReviewRepositoryImpl implements ReviewRepository {

	// 싱글턴으로 리뷰 관리 > 전체 리뷰 관리할 레포 
	private static ReviewRepository repo = new ReviewRepositoryImpl();
	// Integer 연번 
	private Map<Integer, Review> map = new HashMap<>();
	
	// 생성자: 가라로 안에 리뷰들 넣어두기 일단 3개정도만 ?
	private ReviewRepositoryImpl() {
		// id = no++ > 연번 확인
		map.put(1, new Review("힘들어요", "ssafy", "엉엉"));
		map.put(2, new Review("운동해서 놀러갈래", "ssafy1", "한강 피크닉가자"));
		map.put(3, new Review("그럴까요", "ssafy2", "사실 집이 제일 좋아"));
	}
	
	// 싱글턴 -> 인스턴스 하나 생성
	public static ReviewRepository getInstance() {
		return repo;
	}
	
	@Override
	// review 전체 리턴 
	public List<Review> selectAll() {
		
		List<Review> reviewList = new ArrayList<>();
		for (int key : map.keySet()) {
			reviewList.add(map.get(key));
		}
		
		return reviewList;
	}

	// id 기반으로 개별 리뷰 불러오기
	@Override
	public Review select(int reviewId) {
		return map.get(reviewId);
	}

	// 생성한 리뷰 map에 추가 
	@Override
	public boolean insertReview(Review review) {
		// 이미 있는 id면 false 
		if (map.containsKey(review.getReviewId())) return false;
		// map에 연번과 리뷰내용 추가 
		map.put(review.getReviewId(), review);
		return true;
	}

	// 개별 리뷰 수정 
	@Override
	public boolean updateReview(Review review) {
		// id 기반 수정 > 찾는 id 없으면 false 
		if (!map.containsKey(review.getReviewId())) return false;
		// 새 리뷰 변경
		map.put(review.getReviewId(), review);
		return true;
	}

	// 개별 리뷰 삭제
	@Override
	public boolean deleteReview(int reviewId) {
		// 찾는 ID 없으면
		if (!map.containsKey(reviewId)) return false;
		map.remove(reviewId);
		return true;
	}

	// 리뷰 조회당 조회수 늘리기 
	@Override
	public boolean updateViewCnt(int reviewId) {
		// 찾는 ID 없으면
		if (!map.containsKey(reviewId)) return false;
		
		// 리뷰 객체 가져오기
		Review review = map.get(reviewId);
		
		// 조회수 올리기
		review.setViewCnt(review.getViewCnt() + 1);
		return true;
	}

}
