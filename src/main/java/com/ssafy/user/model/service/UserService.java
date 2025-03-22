package com.ssafy.user.model.service;

import java.util.List;
import com.ssafy.user.model.dto.User;

/**
 * 회원 정보, 인증, 관계 및 상호작용 관리를 위한 비즈니스 로직 인터페이스
 */
public interface UserService {
    /**
     * 회원 ID 존재 여부 확인
     * 
     * @param userId 확인할 회원 ID
     * @return 존재 여부
     */
    public abstract boolean userIdExists(String userId);

    /**
     * 회원 인증 처리
     * 
     * @param userId   회원 ID
     * @param password 비밀번호
     * @return 인증 성공 여부, 회원이 존재하지 않거나 비밀번호가 일치하지 않을 경우 false
     */
    public abstract boolean authenticate(String userId, String password);

    /**
     * 회원 가입
     * 
     * @param userId   가입할 회원 ID
     * @param password 비밀번호
     * @return 가입 성공 여부, 중복 ID인 경우 false
     */
    public abstract boolean register(String userId, String password);

    /**
     * 회원 정보 조회
     * 
     * @param userId 조회할 회원 ID
     * @return 회원 DTO, 존재하지 않을 경우 null
     */
    public abstract User select(String userId);

    /**
     * 회원이 팔로우하는 사용자 목록 조회
     * 
     * @param userId 조회할 회원 ID
     * @return 팔로우하는 사용자 ID 목록, 조회 회원 혹은 팔로우하는 사용자가 없을 경우 빈 목록
     */
    public abstract List<String> getFollowings(String userId);

    /**
     * 팔로우 설정
     * 
     * @param userId   팔로우하는 회원 ID
     * @param targetId 팔로우 대상 회원 ID
     * @return 성공 여부, 다음 경우 false: 존재하지 않는 회원, 이미 팔로우 중, 자기 자신을 팔로우
     */
    public abstract boolean follow(String userId, String targetId);

    /**
     * 팔로우 해제
     * 
     * @param userId   팔로우하는 회원 ID
     * @param targetId 팔로우 대상 회원 ID
     * @return 성공 여부, 다음 경우 false: 존재하지 않는 회원, 팔로우 중이 아님
     */
    public abstract boolean unfollow(String userId, String targetId);

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
     * 비디오 좋아요 설정
     * 
     * @param userId  회원 ID
     * @param videoId 비디오 ID
     * @return 성공 여부, 다음 경우 false: 존재하지 않는 회원, 이미 좋아요 중
     */
    public abstract boolean likeVideo(String userId, String videoId);

    /**
     * 비디오 좋아요 해제
     * 
     * @param userId  회원 ID
     * @param videoId 비디오 ID
     * @return 성공 여부, 다음 경우 false: 존재하지 않는 회원, 좋아요 중이 아님
     */
    public abstract boolean unlikeVideo(String userId, String videoId);

    /**
     * 비디오 좋아요 여부 확인
     *
     * @param userId  회원 ID
     * @param videoId 비디오 ID
     */
    public abstract boolean checkLikedVideo(String userId, String videoId);
}
