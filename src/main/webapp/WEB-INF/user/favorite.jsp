<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>좋아요한 영상 목록</title>
<script><%@ include file="/common/likeButton.js" %></script>
</head>
<body>
  <jsp:include page="/common/header.jsp" />
  <h2><c:out value="${sessionScope.loginUser}"/>의 좋아요 한 영상 목록</h2>

  <c:if test="${empty likedvideolist}">
    <p>좋아요한 영상이 없습니다.</p>
  </c:if>

  <c:forEach var="video" items="${likedvideolist}">
    <a href="video?act=reviewPage&youtubeId=${video.youtubeId}"><c:out value="${video.title}"/></a>
    <jsp:include page="/common/likeButton.jsp">
      <jsp:param name="videoId" value="${video.youtubeId}"/>
    </jsp:include>
  </c:forEach>

  <jsp:include page="/common/footer.jsp" />
</body>
</html>
