<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.ssafy.video.model.dto.Video" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>SSAFIT</title>
</head>

<body>
  <jsp:include page="/common/header.jsp" />

  <h2>최근 가장 많이 본 영상</h2>

  <h3>인기 있는 비디오</h3>

  <%-- 정렬 --%>
  <% 
      List<Video> popVideos = (List<Video>)request.getAttribute("videos");
      if (popVideos == null) {
          popVideos = new ArrayList<>();
      } else {
          Collections.sort(popVideos, (o1, o2) -> o2.getViewCnt() - o1.getViewCnt());
          popVideos = popVideos.subList(0, 5);
          request.setAttribute("popVideos", popVideos);
      }
  %>


  <table border="1">
    <tr>
      <th>번호</th>
      <th>제목</th>
      <th>부위</th>
      <th>유튜브 ID</th>
      <th>조회수</th>
    </tr>

    <c:forEach var="video" items="${popVideos}" varStatus="status">
      <tr>
        <td>${status.count}</td>
        <td> <a href="video?act=reviewPage&youtubeId=${video.youtubeId}"> ${video.title}</a></td>
        <td>${video.fitPartName}</td>
        <td>${video.youtubeId}</td>
        <td>${video.viewCnt}</td>
      </tr>
    </c:forEach>
  </table>


  <h2>운동 부위 선택</h2>
  <div class="category-toggle">
    <input type="radio" class="btn-check" name="options-base" id="전신" checked />
    <label for="전신">전신</label>

    <input type="radio" class="btn-check" name="options-base" id="상체" autocomplete="off" />
    <label for="상체">상체</label>

    <input type="radio" class="btn-check" name="options-base" id="하체" autocomplete="off" />
    <label for="하체">하체</label>

    <input type="radio" class="btn-check" name="options-base" id="복부" autocomplete="off" />
    <label for="복부">복부</label>
  </div>

  <table border="1" class="partvideos">
    <tr>
      <th>제목</th>
      <th>부위</th>
      <th>유튜브 ID</th>
      <th>조회수</th>
    </tr>

    <c:forEach var="video" items="${videos}">
      <tr class="partvideo" data-category="${video.fitPartName}">
        <td> <a href="video?act=reviewPage&youtubeId=${video.youtubeId}"> ${video.title}</a></td>
        <td>${video.fitPartName}</td>
        <td>${video.youtubeId}</td>
        <td>${video.viewCnt}</td>
      </tr>
    </c:forEach>
  </table>

  <h2>모든 비디오 선택</h2>
  <a href="video?act=selectAll">
    모든 비디오 확인
  </a>

  <jsp:include page="/common/footer.jsp" />

  <%-- 선택한 부위만 보여주는 스크립트 --%>
  <script>
      document.addEventListener('DOMContentLoaded', function() {
          const categoryToggle = document.querySelectorAll('.category-toggle .btn-check');
          const partVideos = document.querySelectorAll('.partvideos .partvideo');

          categoryToggle.forEach((toggle) => {
              toggle.addEventListener('change', function() {
                  const category = this.id;

                  partVideos.forEach((video) => {
                      if (video.dataset.category === category) {
                          video.style.display = '';
                      } else {
                          video.style.display = 'none';
                      }
                  });
              });
          });

          const checkedRadio = document.querySelector('.btn-check:checked');
          if (checkedRadio) {
              const category = checkedRadio.id;

              partVideos.forEach((video) => {
                  if (video.dataset.category !== category) {
                      video.style.display = 'none';
                  }
              });
          };
      });
  </script>
</body>

</html>
