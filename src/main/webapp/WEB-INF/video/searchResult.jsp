<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ssafy.video.model.dto.Video" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result of Searching</title>
</head>
<body>

	<jsp:include page="/common/header.jsp" />
	
	<title>검색 결과</title>
	
	<h2>검색 결과</h2>
	<h3>검색 결과 개수: ${fn:length(videos)}</h3>
	<c:choose>
        <c:when test="${empty videos}">
            <p>검색 결과가 없습니다.</p>
        </c:when>
        <c:otherwise>
            <table border="1">
                <tr>
                    <th>제목</th>
                    <th>부위</th>
                    <th>유튜브 ID</th>
                    <th>조회수</th>
                </tr>
                <c:forEach var="video" items="${videos}">
                    <tr>
                        <td><a href="video?act=reviewPage&youtubeId=${video.youtubeId}">
                            ${video.title}</a></td>
                        <td>${video.fitPartName}</td>
                        <td>${video.youtubeId}</td>
                        <td>${video.viewCnt}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>


</body>
</html>