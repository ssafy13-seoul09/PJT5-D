package com.ssafy.user.model.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.user.model.dto.User;
import com.ssafy.util.DBUtil;
import com.ssafy.util.DTOFactory;
import com.ssafy.video.model.dto.Video;

public class UserRepositoryImpl implements UserRepository {

    private final DBUtil dbUtil;

    private static final UserRepositoryImpl INSTANCE = new UserRepositoryImpl();

    private UserRepositoryImpl() {
        dbUtil = DBUtil.getInstance();
    }

    public static UserRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public User select(String userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?;";

        return dbUtil.executeQuery(sql,
                pstmt -> pstmt.setString(1, userId),
                rs -> rs.next() ? DTOFactory.createUser(rs) : null);
    }

    @Override
    public boolean insertUser(User user) {
        String sql = "INSERT INTO user (user_id, password) VALUES (?, ?);";

        return dbUtil.executeUpdate(sql,
                pstmt -> {
                    pstmt.setString(1, user.getUserId());
                    pstmt.setString(2, user.getPassword());
                });
    }

    @Override
    public boolean updateUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public boolean deleteUser(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public boolean checkUser(String userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?;";

        return dbUtil.executeQuery(sql,
                pstmt -> pstmt.setString(1, userId),
                ResultSet::next);
    }

    @Override
    public List<String> getFollowings(String userId) {
        String sql = "SELECT * FROM following WHERE follower_id = ?;";

        return dbUtil.executeQuery(sql,
                pstmt -> pstmt.setString(1, userId),
                rs -> {
                    List<String> followings = new ArrayList<>();
                    while (rs.next()) {
                        followings.add(rs.getString("followee_id"));
                    }
                    return followings;
                });
    }

    @Override
    public boolean addFollowing(String userId, String targetId) {
        String sql = "INSERT INTO following (follower_id, followee_id) VALUES (?, ?);";

        return dbUtil.executeUpdate(sql,
                pstmt -> {
                    pstmt.setString(1, userId);
                    pstmt.setString(2, targetId);
                });
    }

    @Override
    public boolean removeFollowing(String userId, String targetId) {
        String sql = "DELETE FROM following WHERE follower_id = ? AND followee_id = ?;";

        return dbUtil.executeUpdate(sql,
                pstmt -> {
                    pstmt.setString(1, userId);
                    pstmt.setString(2, targetId);
                });
    }

    @Override
    public boolean checkFollowing(String userId, String targetId) {
        String sql = "SELECT * FROM following WHERE follower_id = ? AND followee_id = ?;";

        return dbUtil.executeQuery(sql,
                pstmt -> {
                    pstmt.setString(1, userId);
                    pstmt.setString(2, targetId);
                },
                ResultSet::next);
    }

    @Override
    public List<Video> getLikedVideos(String userId) {
        String sql = "SELECT v.* FROM video v JOIN likedvideo l ON v.youtube_id = l.youtube_id WHERE l.user_id = ?;";

        return dbUtil.executeQuery(sql,
                pstmt -> pstmt.setString(1, userId),
                rs -> {
                    List<Video> videos = new ArrayList<>();
                    while (rs.next()) {
                        videos.add(DTOFactory.createVideo(rs));
                    }
                    return videos;
                });
    }

    @Override
    public boolean addLikedVideo(String userId, String videoId) {
        String sql = "INSERT INTO likedvideo (user_id, youtube_id) VALUES (?, ?);";

        return dbUtil.executeUpdate(sql,
                pstmt -> {
                    pstmt.setString(1, userId);
                    pstmt.setString(2, videoId);
                });
    }

    @Override
    public boolean removeLikedVideo(String userId, String videoId) {
        String sql = "DELETE FROM likedvideo WHERE user_id = ? AND youtube_id = ?;";

        return dbUtil.executeUpdate(sql,
                pstmt -> {
                    pstmt.setString(1, userId);
                    pstmt.setString(2, videoId);
                });
    }

    @Override
    public boolean checkLikedVideo(String userId, String videoId) {
        String sql = "SELECT * FROM likedvideo WHERE user_id = ? AND youtube_id = ?;";

        return dbUtil.executeQuery(sql,
                pstmt -> {
                    pstmt.setString(1, userId);
                    pstmt.setString(2, videoId);
                },
                ResultSet::next);
    }
}
