<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팔로잉 추천</title>
<script><%@ include file="/common/followButton.js" %></script>
</head>
<body>
  <jsp:include page="/common/header.jsp" />
  <h2>아래 사용자들을 추천합니다.</h2>

  <c:if test="${empty recommendedUsers}">
    <p>추천할 사용자가 없습니다.</p>
  </c:if>

  <c:forEach var="userId" items="${recommendedUsers}">
    <p><c:out value="${userId}"/></p>
    <jsp:include page="/common/followButton.jsp">
      <jsp:param name="targetId" value="${userId}"/>
    </jsp:include>
  </c:forEach>

  <jsp:include page="/common/footer.jsp" />
</body>
</html>
