<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 상세보기</title>
</head>
<body>

	<h2>${review.title }</h2>
	<div>
		${review.contents}
	</div>
	
    <c:if test="${not empty sessionScope.loginUser}">
      <c:if test="${sessionScope.loginUser.toString() eq review.authorId}">
	    <!-- 삭제 & 수정 -->
	    <a href="review?act=remove&reviewId=${review.reviewId}">삭제</a>
	    <!--  updateform 불러오기 -->
	    <a href="review?act=updateform&reviewId=${review.reviewId}">수정</a>
      </c:if>
      <c:if test="${sessionScope.loginUser.toString() ne review.authorId}">
        <a href="user?act=follow&id=${review.authorId}">팔로우</a>
        <a href="user?act=unfollow&id=${review.authorId}">언팔로우</a>
      </c:if>
    </c:if>

	  <!-- 목록 보기 -->
	  <!-- <a href="video?act=detail&youtubeId=${review.youtubeId}">돌아가기</a> -->

</body>
</html>
