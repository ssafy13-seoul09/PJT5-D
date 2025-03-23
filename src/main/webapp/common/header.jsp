<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<header>
  <c:if test="${empty sessionScope.loginUser}">
    <a href="user?act=loginform">로그인</a>
    <a href="user?act=registform">회원가입</a>
    <a href="index.jsp">메인페이지</a>
  </c:if>
  <c:if test="${not empty sessionScope.loginUser}">
    ${sessionScope.loginUser}님 환영합니다.
    <a href="user?act=logout">로그아웃</a>
    <a href="user?act=mypage">마이페이지</a>
    <a href="index.jsp">메인페이지</a>
  </c:if>
</header>
