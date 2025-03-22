## 역할

- 회원 관련 기능 구현(MVC 패턴)
  - 회원 가입
  - 회원 로그인/로그아웃
  - 회원 팔로우/언팔로우
  - 영상 좋아요/좋아요 취소
  - 마이페이지
    - 팔로우 회원 목록 조회
    - 좋아요 영상 목록 조회
- DB 설계 총괄 

## 기능 구현

### 데이터 베이스 설계

팀원들과 상의하여 기초 도메인: 회원(User), 영상(Video), 리뷰(Review) 별로 나누어 설계, 담당 파트를 나눔

영상은 유튜브 영상만 지원하므로 해당 사이트에서 사용하는 영상 ID를 PK로 사용.

유저는 가입 시 사용할 id를 PK로 사용.

리뷰는 생성 순서대로 번호를 부여하는 것으로(`AUTO_INCREMENT`) PK를 생성.

회원이 좋아요한 영상, 회원이 팔로우한 회원은 별도의 테이블(`likedvideo`, `following`)을 생성하여 관리.
회원 id, 영상 id를 FK로 사용하여 참조하도록 설계.

처음엔 참조 테이블의 PK를 따로 두지 않았었으나, 같은 데이터가 중복되는 문제가 발생하여 두 FK를 복합 키로 PK로 사용하도록 변경.

회원 A가 회원 A를 팔로우하는(자기 자신을 팔로우하는) 문제가 생겨 이후 제약 조건을 추가하여 해결.

`CONSTRAINT check_different CHECK (follower_id != followee_id)`

### 회원 로그인/로그아웃 기능

회원 로그인은 세션을 이용해서 구현.

로그인 페이지는 여러 알림을 보여줄 필요가 있음:
- 로그인 실패 알림
- 로그인이 필요한 페이지 접근 시 로그인 필요 알림

이에 `login.jsp`에 다음 코드를 추가:
```jsp
<c:if test="${!empty msg}">
  <script>
    alert("${msg}");
  </script>
</c:if>
```

알림 메시지가 필요한 경우 자바스크립트 `alert` 함수를 이용해 알림을 띄울 수 있도록 함.

로그인이 필요한 페이지 접근 시 로그인 페이지로 이동하도록 `UserController`에 아래 헬퍼 메서드 추가: 
```java
/**
 * 로그인 여부 확인
 * 로그인 되어 있지 않으면 로그인 페이지로 이동
 * 
 * @return 로그인 여부
 */
private boolean checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (req.getSession().getAttribute("loginUser") == null) {
        req.setAttribute("msg", "로그인이 필요합니다.");
        req.getRequestDispatcher(PAGE_LOGIN).forward(req, resp);
        return false;
    }
    return true;
}
```

혹은 JSP 페이지 안에 조건문을 활용해 처리하도록 구현.

```jsp
<c:if test="${empty sessionScope.loginUser}">
  <a href="user?act=loginform">로그인</a>
  <a href="user?act=registform">회원가입</a>
</c:if>
<c:if test="${not empty sessionScope.loginUser}">
  ${sessionScope.loginUser}님 환영합니다.
  <a href="user?act=logout">로그아웃</a>
  <a href="user?act=mypage">마이페이지</a>
</c:if>
```

### 회원 가입 기능

회원 가입은 POST 방식으로 처리.

이미 존재하는 회원일 경우 알림과 함께 다시 회원 가입 페이지로 이동하도록 구현.

가입 성공 시 로그인 페이지로 이동하도록 구현.

### 회원 팔로우/언팔로우 및 영상 좋아요/취소 기능

`act=follow&id={팔로우할 회원 id}`로 팔로우, `act=unfollow&id={언팔로우할 회원 id}`로 언팔로우.
`act=likevideo&id={좋아요할 영상 id}`로 좋아요, `act=unlikevideo&id={좋아요 취소할 영상 id}`로 좋아요 취소.

행동의 주체가 되는 회원의 아이디는 세션에 저장된 로그인한 회원의 아이디를 사용.

해당 기능은 다양한 페이지에서 사용될 수 있으므로 요청을 보낸 페이지로 다시 돌아갈 수 있어야 함.
요청 헤더의 `referer`를 이용해 요청을 보낸 페이지로 다시 이동하도록 구현.

```java
private void doFollow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (!checkLogin(req, resp)) {
        return;
    }

    String userId = getLoginUser(req);
    String targetId = req.getParameter("id");
    String referer = req.getHeader("referer");
    userService.follow(userId, targetId);

    resp.sendRedirect(referer);
}
```

처음 기능 구현은 팔로우/언팔로우 각각 링크를 두어 구현했으나, 이후 회원의 팔로우 여부에 따라 링크를 다르게 보여주어 토글 느낌의 상호작용이 가능하도록 변경.

먼저 자바스크립트의 `fetch` 함수를 이용해 현재 회원의 팔로우/좋아요 여부를 확인하고, 버튼을 누르면 해당 기능을 수행하도록 구현.

```javascript
fetch('user?act=checkfollow&id=' + targetId)
  .then(response => {
    if (!response.ok) {
      throw new Error('서버 응답 오류');
    }
    return response.text();
  })
  .then(result => {
    const isfollowed = result === 'true';
    updateButtonState(followBtn, unfollowBtn, isfollowed);
  })
  .catch(error => {
    console.error('팔로우 상태 확인 중 오류 발생:', error);
  });
```

이를 위해 `checkfollow` 액션은 응답을 `true` 또는 `false`로 보내도록 구현.

```java
  private void doCheckFollow(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      String userId = getLoginUser(req);
      String targetId = req.getParameter("id");
      resp.getWriter().print(userService.checkFollowing(userId, targetId));
  }
```

팔로우/좋아요 토글 버튼은 여러 페이지에서 사용될 수 있고, 한 페이지에 여러 개가 사용될 수 있으므로 별도의 JSP파일로 분리하여 사용.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${not empty sessionScope.loginUser}">
  <div class="follow-button-component" data-target-id="${param.targetId}">
    <a href="#" class="follow-btn">👍 팔로우</a>
    <a href="#" class="unfollow-btn" style="display: none;">💔 팔로우 취소</a>
  </div>
</c:if>
```

사용할 페이지에서는 자바스크립트 코드를 include 지시자를 이용해 추가하고,
jsp의 `include` 태그를 이용해 버튼을 추가하도록 함.
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팔로잉 목록</title>
<script><%@ include file="/common/followButton.js" %></script>
</head>
<body>
  <jsp:include page="/common/header.jsp" />
  <h2><c:out value="${sessionScope.loginUser}"/>의 팔로잉 목록</h2>

  <c:if test="${empty followlist}">
    <p>팔로잉한 유저가 없습니다.</p>
  </c:if>

  <c:forEach var="follow" items="${followlist}">
    <p><c:out value="${follow}"/></p>
    <jsp:include page="/common/followButton.jsp">
      <jsp:param name="targetId" value="${follow}"/>
    </jsp:include>
  </c:forEach>

  <jsp:include page="/common/footer.jsp" />
</body>
</html>

```

### 인기 비디오 조회 및 운동 부위 별 영상 조회 기능

최초 기능 설계 시 url을 통해 인기 비디오나 운동 부위 별 영상을 조회하도록 구현.

이 때 두 가지 문제가 발생:
1. 인기 비디오와 운동 부위별 영상을 함께 조회할 수 없음
2. 최초 접속 시 아무 비디오도 보이지 않음.

이에 페이지에는 전체 영상을 제공하고, JSP와 자바스크립트를 이용해 인기 비디오와 운동 부위별 영상을 필터링하여 보여주도록 변경.

인기 비디오는 JSP Scriptlet을 이용해 구현. 
운동 부위별 영상은 자바스크립트를 이용해 선택되지 않은 영상을 숨기는(`display: none`) 방식으로 구현.

이를 `mainPage.jsp`에 구현하고, `index.jsp`는 해당 페이지를 담당하는 컨트롤러로 포워딩만 하도록 변경.

## 느낀점

이것이 협업이구나!를 느낄 수 있었던 프로젝트였다.

초기 설계부터 업무 분장, 이름 짓는 규칙까지 팀원들과 사전에 많은 의논을 거쳤다.
하지만 막상 코드를 짜다 보니 설계가 부족한 부분도 있었고, 서로 연관되는 부분도 많아 다시 조율하다보니 신속한 코드 작성이 어려웠다.

설계와 협업 전략은 정말 경험을 통해 익히는 것이라는 생각이 들었다.

git으로 이런 저런 기능 써보면서 민폐도 많이 끼쳐보고, 다른 사람 코드를 고쳐주는 과정에서 "내 마음대로" 짜지 않는 법에 대한 고민도 많이 했다.

다 같이 실시간으로 작업하는 환경에서 어떻게하면 깔끔하고 빠르게 작업할 수 있을지에 대한 고민도 많이 들었다.

앞으로 더 많은 프로젝트를 통해 협업 능력을 키워나가야 겠다는 생각이 드는 프로젝트였다.
