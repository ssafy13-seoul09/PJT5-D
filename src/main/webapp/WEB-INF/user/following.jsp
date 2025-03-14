<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팔로잉 목록</title>
</head>
<body>
  <h2><c:out value="${sessionScope.loginUser}"/>의 팔로잉 목록</h2>

  <c:if test="${empty followlist}">
    <p>팔로잉한 유저가 없습니다.</p>
  </c:if>

  <c:forEach var="follow" items="${followlist}">
    <p><c:out value="${follow}"/></p>
  </c:forEach>

</body>
</html>
