package com.ssafy.video.model.repository;
import com.ssafy.video.model.dto.Video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.video.model.dto.Video;

public class VideoRepositoryImpl implements VideoRepository{
	// 영화를 담을 객체를 Singleton Pattern으로 관리해준다.
	private static VideoRepository repo = new VideoRepositoryImpl();
	
	// Video를 담을 Map을 형성해준다.
	private Map<Integer, Video> map = new HashMap<>();
	
	// 생성한 객체에 Map 형식으로 데이터를 관리한다.
	private VideoRepositoryImpl() {
		map.put(1, new Video("https://www.youtube.com/watch?v=7TLk7pscICk", "채널1", 10, "복부", "누워서 5분 복부"));
		map.put(2, new Video("https://www.youtube.com/watch?v=cMkZ6A7wngk", "채널2", 15, "전신", "전신 올인원"));
		map.put(3, new Video("https://www.youtube.com/watch?v=4kZHHPH6heY", "채널3", 18, "전신", "전신운동 근력 유산소"));
		map.put(4, new Video("https://www.youtube.com/watch?v=DehgWgRde-I", "채널4", 25, "복부", "악마의 복근 운동"));
		map.put(5, new Video("https://www.youtube.com/watch?v=DWYDL-WxF1U", "채널5", 35, "하체", "하체날, 딱 10분밖에 없다면-스쿼트 10가지 동작"));
		map.put(6, new Video("https://www.youtube.com/watch?v=Hx8Lc_0hUaI", "채널6", 28, "상체", "운동할 시간이 없다는 사람에게 보여주세요... 제발"));
		
	}
	
	public static VideoRepository getInstance() {
		return repo;
	}
	
	@Override
	public List<Video> selectAll() {
		
		// 모든 key에 대한 데이터를 ArrayList에 넣고, 반환한다.
		List<Video> tmp = new ArrayList<>();
		for(int key: map.keySet()) {
			tmp.add(map.get(key));
		}
		
		return tmp;
	}

	@Override
	public Video select(String youtubeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateViewCnt(String youtubeId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
