package com.ssafy.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.review.model.dto.Review;
import com.ssafy.user.model.dto.User;
import com.ssafy.video.model.dto.Video;

/**
 * SQL ResultSet을 DTO로 변환하는 팩토리 클래스
 */
public class DTOFactory {
    public static User createUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getString("user_id"));
        user.setPassword(rs.getString("password"));
        return user;
    }

    public static Video createVideo(ResultSet rs) throws SQLException {
        Video video = new Video();
        video.setYoutubeId(rs.getString("youtube_id"));
        video.setTitle(rs.getString("title"));
        video.setFitPartName(rs.getString("fitpart_name"));
        video.setChannelName(rs.getString("channel_name"));
        video.setViewCnt(rs.getInt("view_count"));
        return video;
    }

    public static Review createReview(ResultSet rs) throws SQLException {
        Review review = new Review();
        review.setReviewId(rs.getInt("review_id"));
        review.setTitle(rs.getString("title"));
        review.setAuthorId(rs.getString("author_id"));
        review.setContents(rs.getString("content"));
        review.setCreatedAt(rs.getTimestamp("created_at"));
        review.setViewCnt(rs.getInt("view_count"));
        review.setYoutubeId(rs.getString("youtube_id"));
        return review;
    }

}
