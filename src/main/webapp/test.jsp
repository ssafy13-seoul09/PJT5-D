<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.ssafy.video.model.dto.Video" %>   
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>  

<html>
<head>
<meta charset="UTF-8">
<title>개별 비디오: 리뷰 목록 불러오기 테스트 </title>
</head>
<body>
	상세보기 페이지 test
	<!-- 비디오 누르면 리뷰 불러오기 테스트용 -->
    <c:forEach var="video" items="${videoList}">
        <li>
            <a href="/videos/${video.youtubeId}">${video.title} 상세보기</a>
        </li>
        <li>
        	<!-- 비디오 클릭 시 특정 비디오 상세 페이지로 이동 -->
        	<a href="/video?act=selectVid&youtubeId=${video.youtubeId}">${video.title} 상세보기</a>
        </li>
    </c:forEach>

</body>
</html>