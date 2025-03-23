<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 수정</title>
</head>
<body>
  <jsp:include page="/common/header.jsp" />
	<h2>게시글 수정</h2>
	<form action="review" method="POST">
		<input type="hidden" name="act" value="update">
		<input type="hidden" name="id" value="${review.reviewId }">
		<div>제목 : <input type="text" name="title" value="${review.title }"></div>
		<div>작성자 : <input type="text" name="writer"  readonly="readonly" value="${review.authorId }"></div>
		<div>내용 : <textarea rows="10" cols="30" name="contents">${review.contents }</textarea></div>
		<button>게시글수정</button>		
	</form>
	<a href="video?act=reviewPage&youtubeId=${youtubeId}">돌아가기</a>
  <jsp:include page="/common/footer.jsp" />
</body>
</html>
