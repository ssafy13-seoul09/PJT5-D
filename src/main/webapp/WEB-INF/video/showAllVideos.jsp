<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import ="com.ssafy.video.model.dto.Video" %>   
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// req에서 받은 데이터를 pageContext에서 변수로 저장하고, 하나씩 출력한다.
		List<Video> videoList = (List<Video>)request.getAttribute("list");	
		
		pageContext.setAttribute("videoList", videoList);
	%>
	<h1>모든 비디오 확인</h1>
	<%-- 
	<%= videoList %>
	 --%>
	
	
	<table border ="1">
		<tr>
			<th>번호</th>
			<th>채널명</th>
			<th>부위</th>
			<th>유튜브 ID</th>
			<th>조회수</th>
		</tr>
		
		<c:forEach var = "video" items = "${videoList}" varStatus = "status">
				<%-- ${status.count} : ${movie}<br> --%>
				<tr>
					<td>${status.count}</td>
					 <td> <a href = "video?act=reviewPage&youtubeId=${video.youtubeId}"> ${video.title}</a></td>
					<td>${video.getFitPartName()}</td>
					<td>${video.getYoutubeId()}</td>
					<td>${video.getViewCnt()}</td>
					<%-- <td>${video.getReviews()}</td>	 --%>
				</tr>
								
		</c:forEach>
	
	</table>
	
	<a href = "index.jsp">홈으로</a>
	
</body>
</html>
