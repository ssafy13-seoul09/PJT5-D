package com.ssafy.video.model.service;

import java.util.List;

import com.ssafy.video.model.dto.Video;

/**
 * 영상 정보, 조회수, 부위별 영상 관리를 위한 비즈니스 로직 인터페이스
 */
public interface VideoService {

    public abstract Video select(String youtubeId);

    public abstract List<Video> selectAll();

    public boolean updateViewCnt(String youtubeId);
}
