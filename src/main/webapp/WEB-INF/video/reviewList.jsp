<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 전체 리스트 보기</title>
</head>
<body>

	<table>
		<tr>
			<th>ID</th>
			<th>제목</th>
			<th>쓰니</th>
			<th>조회수</th>
			<th>등록일</th>
		</tr>
		<c:forEach items="${list}" var="review">
			<tr>
				<td>${review.reviewId}</td>
				<!-- 리뷰 상세보기로 링크 연결 --> 
				<td><a href="review?act=detail&reviewId=${review.reviewId}">${review.title}</a></td>
				<td>${review.authorId }</td>
				<td>${review.viewCnt }</td>
				<td>${review.createdAt }</td>
			</tr>
		</c:forEach>
	
	</table>

</body>
</html>