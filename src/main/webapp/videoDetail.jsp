<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>${video.title} 상세보기</title>
</head>
<body>

<h1>${video.title}</h1>
<p>${video.description}</p>

<h2>리뷰 목록</h2>
<ul>
    <c:forEach var="review" items="${video.reviews}">
        <li>${review.content} (⭐${review.rating})</li>
    </c:forEach>
</ul>

<!-- 비디오 목록으로 돌아가기 -->
<a href="/video?act=selectAll">비디오 목록으로 돌아가기</a>

</body>
</html>
