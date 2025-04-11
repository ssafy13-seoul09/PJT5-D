<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
  <jsp:include page="/common/header.jsp" />
    <h2><c:out value="${sessionScope.loginUser}"/>의 마이페이지</h2>
    <a href="user?act=logout">로그아웃</a>
    <a href="user?act=likedVideoList">좋아요한 영상 목록</a>
    <a href="user?act=followList">팔로잉 목록</a>
    <a href="user?act=doRecommendFollow">팔로잉 목록</a>
  <jsp:include page="/common/footer.jsp" />
</body>
</html>
