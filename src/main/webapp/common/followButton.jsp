<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${not empty sessionScope.loginUser}">
  <div class="follow-button-component" data-target-id="${param.targetId}">
    <a href="#" class="follow-btn">👍 팔로우</a>
    <a href="#" class="unfollow-btn" style="display: none;">💔 팔로우 취소</a>
  </div>
</c:if>
