<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팔로잉 목록</title>
<script><%@ include file="/common/followButton.js" %></script>
</head>
<body>
  <jsp:include page="/common/header.jsp" />
  <h2><c:out value="${sessionScope.loginUser}"/>의 팔로잉 목록</h2>

  <c:if test="${empty followList}">
    <p>팔로잉한 유저가 없습니다.</p>
  </c:if>

  <c:forEach var="follow" items="${followList}">
    <p><c:out value="${follow}"/></p>
    <jsp:include page="/common/followButton.jsp">
      <jsp:param name="targetId" value="${follow}"/>
    </jsp:include>
  </c:forEach>

  <jsp:include page="/common/footer.jsp" />
</body>
</html>
