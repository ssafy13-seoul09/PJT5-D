<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.ssafy.video.model.dto.Video" %>   
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSAFIT</title>
</head>
<body>
	<h2>최근 가장 많이 본 영상</h2>
	
	<table border ="1">
		<tr>
			<th>번호</th>
			<th>채널명</th>
			<th>부위</th>
			<th>유튜브 링크</th>
			<th>조회수</th>
		</tr>
		
		<c:forEach var = "video" items = "${fullBody}" varStatus = "status">
				<%-- ${status.count} : ${movie}<br> --%>
				<tr>
					<td>${status.count}</td>
					<td>${video.channelName}</td>
					<td>${video.fitPartName}</td>
					<td>${video.youtubeId}</td>
					<td>${video.viewCnt}</td>
					<%-- <td>${video.getReviews()}</td>	 --%>
				</tr>
								
		</c:forEach>
	
	</table>
	
	
	
	
	<a href = "video?act=favoriteList">
		Video1
	</a>
	
	<a href = "video?act=favoriteList">
		Video2
	</a>
	
	<a href = "video?act=favoriteList">
		Video3
	</a>
	
	
	<h2>운동 부위 선택</h2>
	
	<a href = "video?act=bodypartList&bodyPart=전신">
		전신
	</a>
	
	<a href = "video?act=bodypartList&bodyPart=복부">
		복부
	</a>
	
	<a href = "video?act=bodypartList&bodyPart=상체">
		상체
	</a>
	
	<a href = "video?act=bodypartList&bodyPart=하체">
		하체
	</a>
	
	
	<h2>모든 비디오 선택</h2>
	<a href = "video?act=selectAll">
		Video1
	</a>

</body>
</html>
