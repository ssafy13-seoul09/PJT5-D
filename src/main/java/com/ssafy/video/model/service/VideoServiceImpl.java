package com.ssafy.video.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.review.model.dto.Review;
import com.ssafy.review.model.repository.ReviewRepository;
import com.ssafy.review.model.repository.ReviewRepositoryImpl;
import com.ssafy.video.model.dto.Video;
import com.ssafy.video.model.repository.VideoRepository;
import com.ssafy.video.model.repository.VideoRepositoryImpl;

public class VideoServiceImpl implements VideoService{
	// DB에서 데이터를 가져와서 Service에서 처리하는 방식으로 코드를 작성하면 된다.
	private static VideoService service = new VideoServiceImpl();
	private VideoRepository repo = VideoRepositoryImpl.getInstance();
	
	// VideoServiceImpl 객체인 service를 하나만 생성하고, service를 통해서 하나의 데이터 모음을 활용한다.
	private VideoServiceImpl() {
	}
	
	public static VideoService getInstance() {
		return service;
	}
	
	
	
	@Override
	public List<Video> selectAll() {		
		return repo.selectAll();
	}

	@Override
	public List<Video> selectPopularVideos(int viewCnt) {
		List<Video> allVideos = repo.selectAll();
		
		
		return null;
	}

	@Override
	public List<Video> selectBodypartVideos(String fitBodyPart) {
		
		
		return null;
	}

	@Override
	public void getReviewbyId(String youtubeId) {
		
		// db에서 가져와야 하므로 repo 호출  
		repo.getReviewbyId(youtubeId);
	}

	@Override
	public Video select(String youtubeId) {
		return repo.select(youtubeId);
	}
	

}
