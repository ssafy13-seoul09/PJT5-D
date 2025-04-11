package com.ssafy.video.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.util.SearchUtil;
import com.ssafy.util.ValidationUtils;
import com.ssafy.video.model.dto.Video;
import com.ssafy.video.model.repository.VideoRepository;
import com.ssafy.video.model.repository.VideoRepositoryImpl;

public class VideoServiceImpl implements VideoService {

    private final VideoRepository repo;

    private static final VideoService INSTANCE = new VideoServiceImpl();

    private VideoServiceImpl() {
        repo = VideoRepositoryImpl.getInstance();
    }

    public static VideoService getInstance() {
        return INSTANCE;
    }

    @Override
    public Video select(String youtubeId) {
        return ValidationUtils.checkYoutubeId(youtubeId) ? repo.select(youtubeId) : null;
    }

    @Override
    public List<Video> selectAll() {
        return repo.selectAll();
    }

    @Override
    public boolean updateViewCnt(String youtubeId) {
        return ValidationUtils.checkYoutubeId(youtubeId) && repo.updateViewCnt(youtubeId);
    }
    
    @Override
    public List<Video> searchByTitle(String keyword) {
    	List<Video> videos = selectAll();
    	
    	List<Video> result = new ArrayList<>();
    	
    	for (Video video : videos) {
            if (SearchUtil.KMP(video.getTitle().toLowerCase(), keyword.toLowerCase())) {
                result.add(video);
            }
        }
    	
        return result;
    }


}
