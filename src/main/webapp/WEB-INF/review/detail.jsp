<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 상세보기</title>
</head>
<body>

	<h2>${review.title }</h2>
	<div>
		${review.content}
	</div>
	
	<!-- 삭제 & 수정 -->
	<a href="review?act=remove&reviewId=${review.reviewId}">삭제</a>
	<!--  updateform 불러오기 -->
	<a href="review?act=updateform&reviewId=${review.reviewId}">수정</a>

</body>
</html>