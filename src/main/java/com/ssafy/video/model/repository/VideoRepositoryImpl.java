package com.ssafy.video.model.repository;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.review.model.dto.Review;
import com.ssafy.util.DBUtil;
import com.ssafy.util.DTOFactory;
import com.ssafy.video.model.dto.Video;

public class VideoRepositoryImpl implements VideoRepository {

    private final DBUtil dbUtil;

    private static final VideoRepositoryImpl INSTANCE = new VideoRepositoryImpl();

    private VideoRepositoryImpl() {
        dbUtil = DBUtil.getInstance();
    }

    public static VideoRepository getInstance() {
        return INSTANCE;
    }

    // 개별 video 불러오기
    @Override
    public Video select(String youtubeId) {
        String sql = "SELECT * FROM video WHERE youtube_id=?";

        return dbUtil.executeQuery(sql,
                pstmt -> pstmt.setString(1, youtubeId),
                rs -> rs.next() ? DTOFactory.createVideo(rs) : null);
    }

    @Override
    public List<Video> selectAll() {
        String sql = "SELECT * FROM video";

        return dbUtil.executeQuery(sql,
                pstmt -> {
                },
                rs -> {
                    List<Video> videos = new ArrayList<>();
                    while (rs.next()) {
                        videos.add(DTOFactory.createVideo(rs));
                    }
                    return videos;
                });
    }

    @Override
    public boolean updateViewCnt(String youtubeId) {
        String sql = "UPDATE video SET view_count = view_count + 1 WHERE youtube_id=?";

        return dbUtil.executeUpdate(sql,
                pstmt -> pstmt.setString(1, youtubeId));
    }

    // youtubeId와 같은 id 가지는 리뷰 불러오기
    @Override
    public List<Review> getReviews(String youtubeId) {
        throw new UnsupportedOperationException();
    }
}
