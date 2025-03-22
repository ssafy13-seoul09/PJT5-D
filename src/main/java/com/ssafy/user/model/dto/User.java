package com.ssafy.user.model.dto;

/**
 * 회원 데이터 전송을 위한 DTO
 *
 * - 비밀번호는 평문으로 저장됨에 주의
 * - 사용자는 userId로 고유하게 식별됨
 */
public class User {
    private String userId;
    private String password;

    public User() {
    }

    public User(String userId, String password) {
        this.userId = userId; // 식별자
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 비밀번호 제외
    @Override
    public String toString() {
        return userId;
    }
}
