package com.ssafy.user.model.repository;

import java.util.List;

import com.ssafy.user.model.dto.User;

/**
 * 회원 정보 및 관계/상호작용 관리를 위한 데이터 접근 인터페이스
 */
public interface UserRepository {

    // 회원 정보 관리

    /**
     * 회원 정보 조회
     * 
     * @param userId 조회할 회원 ID
     * @return 회원 DTO, 존재하지 않을 경우 null
     */
    public abstract User select(String userId);

    /**
     * 회원 등록
     *
     * @param user 등록할 회원 DTO
     * @return 등록 성공 여부, 실패 혹은 중복 ID인 경우 false
     */
    public abstract boolean insertUser(User user);

    public abstract boolean updateUser(User user);

    public abstract boolean deleteUser(String userId);

    public abstract boolean checkUser(String userId);

    // 회원 관계 관리

    /**
     * 회원이 팔로우하는 사용자 목록 조회
     *
     * @param userId 조회할 회원 ID
     * @return 팔로우하는 사용자 ID 목록, 조회 회원 혹은 팔로우하는 사용자가 없을 경우 빈 목록
     */
    public abstract List<String> getFollowings(String userId);

    /**
     * 팔로잉 추가
     *
     * @param userId   팔로우하는 회원 ID
     * @param targetId 팔로우 대상 회원 ID
     * @return 추가 성공 여부, 다음 경우 false: 존재하지 않는 회원, 이미 팔로우 중, 자기 자신을 팔로우
     */
    public abstract boolean addFollowing(String userId, String targetId);

    /**
     * 팔로잉 삭제
     *
     * @param userId   팔로우하는 회원 ID
     * @param targetId 팔로우 대상 회원 ID
     * @return 삭제 성공 여부, 다음 경우 false: 존재하지 않는 회원, 팔로우 중이 아님
     */
    public abstract boolean removeFollowing(String userId, String targetId);

    /**
     * 팔로잉 여부 확인
     *
     * @param userId   팔로우하는 회원 ID
     * @param targetId 팔로우 대상 회원 ID
     */
    public abstract boolean checkFollowing(String userId, String targetId);

    /**
     * 회원이 좋아요한 비디오 목록 조회
     *
     * @param userId 조회할 회원 ID
     * @return 좋아요한 비디오 ID 목록, 조회 회원 혹은 좋아요한 비디오가 없을 경우 빈 목록
     */
    public abstract List<String> getLikedVideos(String userId);

    /**
     * 좋아요한 비디오 추가
     *
     * @param userId  회원 ID
     * @param videoId 비디오 ID
     * @return 추가 성공 여부, 다음 경우 false: 존재하지 않는 회원, 이미 좋아요 중
     */
    public abstract boolean addLikedVideo(String userId, String videoId);

    /**
     * 좋아요한 비디오 삭제
     *
     * @param userId  회원 ID
     * @param videoId 비디오 ID
     * @return 삭제 성공 여부, 다음 경우 false: 존재하지 않는 회원, 좋아요 중이 아님
     */
    public abstract boolean removeLikedVideo(String userId, String videoId);

    /**
     * 비디오 좋아요 여부 확인
     *
     * @param userId  회원 ID
     * @param videoId 비디오 ID
     */
    public abstract boolean checkLikedVideo(String userId, String videoId);
}
