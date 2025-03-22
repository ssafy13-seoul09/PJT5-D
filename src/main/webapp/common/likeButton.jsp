<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${not empty sessionScope.loginUser}">
  <div class="like-button-component" data-video-id="${param.videoId}">
    <a href="#" class="like-btn">👍 좋아요</a>
    <a href="#" class="unlike-btn" style="display: none;">💔 좋아요 취소</a>
  </div>
</c:if>
