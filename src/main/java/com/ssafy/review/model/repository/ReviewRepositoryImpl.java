package com.ssafy.review.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.review.model.dto.Review;
import com.ssafy.util.DBUtil;
import com.ssafy.util.DTOFactory;

public class ReviewRepositoryImpl implements ReviewRepository {

    private final DBUtil dbUtil;

    private static final ReviewRepository INSTANCE = new ReviewRepositoryImpl();

    private ReviewRepositoryImpl() {
        dbUtil = DBUtil.getInstance();
    }

    public static ReviewRepository getInstance() {
        return INSTANCE;
    }

    // 리뷰 1개 찾기
    @Override
    public Review select(int reviewId) {
        String sql = "SELECT * FROM review WHERE review_id = ?";

        return dbUtil.executeQuery(sql,
                pstmt -> pstmt.setInt(1, reviewId),
                rs -> rs.next() ? DTOFactory.createReview(rs) : null);
    }

    // 리뷰 새로 생성
    @Override
    public boolean insertReview(Review review) {
        String sql = "INSERT INTO review(youtube_id, author_id, title, content) VALUES(?, ?, ?, ?)";

        return dbUtil.executeUpdate(sql,
                pstmt -> {
                    pstmt.setString(1, review.getYoutubeId());
                    pstmt.setString(2, review.getAuthorId());
                    pstmt.setString(3, review.getTitle());
                    pstmt.setString(4, review.getContents());
                });
    }

    // 개별 리뷰 수정
    @Override
    public boolean updateReview(Review review) {
        String sql = "UPDATE review SET author_id = ?, title = ?, content = ? WHERE review_id = ?";

        return dbUtil.executeUpdate(sql,
                pstmt -> {
                    pstmt.setString(1, review.getAuthorId());
                    pstmt.setString(2, review.getTitle());
                    pstmt.setString(3, review.getContents());
                    pstmt.setInt(4, review.getReviewId());
                });
    }

    // 개별 리뷰 삭제
    @Override
    public boolean deleteReview(int reviewId) {
        String sql = "DELETE FROM review WHERE review_id = ?";

        return dbUtil.executeUpdate(sql,
                pstmt -> pstmt.setInt(1, reviewId));
    }

    @Override
    public List<Review> selectAll() {
        String sql = "SELECT * FROM review";

        return dbUtil.executeQuery(sql,
                pstmt -> {
                },
                rs -> {
                    List<Review> reviews = new ArrayList<>();
                    while (rs.next()) {
                        reviews.add(DTOFactory.createReview(rs));
                    }
                    return reviews;
                });
    }

    // youtubeId와 일치하는 리뷰 리스트 반환하기 (videorepo와 소통)
    @Override
    public List<Review> getReviews(String youtubeId) {
        String sql = "SELECT * FROM review WHERE youtube_id = ?";

        return dbUtil.executeQuery(sql,
                pstmt -> pstmt.setString(1, youtubeId),
                rs -> {
                    List<Review> reviews = new ArrayList<>();
                    while (rs.next()) {
                        reviews.add(DTOFactory.createReview(rs));
                    }
                    return reviews;
                });
    }

    // 리뷰 조회당 조회수 늘리기
    @Override
    public boolean updateViewCnt(int reviewId) {
        String sql = "UPDATE review SET view_count = view_count + 1 WHERE review_id = ?";

        return dbUtil.executeUpdate(sql,
                pstmt -> pstmt.setInt(1, reviewId));
    }
}
