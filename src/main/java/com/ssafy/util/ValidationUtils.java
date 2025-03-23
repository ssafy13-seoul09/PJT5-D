package com.ssafy.util;

/**
 * 유효성 검사 유틸리티
 *
 * 주요 데이터에 대한 기본 형식 검사 메서드 제공
 */
public class ValidationUtils {
    public static boolean checkUserId(String userId) {
        return userId != null && userId.matches("^[a-zA-Z0-9_]{1,255}$");
    }

    public static boolean checkReviewId(int reviewId) {
        return reviewId > 0;
    }

    public static boolean checkYoutubeId(String youtubeId) {
        return youtubeId != null && youtubeId.matches("^[a-zA-Z0-9_-]{11}$");
    }
}
