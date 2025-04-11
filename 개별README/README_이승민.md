## 역할

- BFS를 활용한 팔로우 추천 시스템

### UserService

기존 회원 팔로우 기능을 활용하여 새로 팔로우 할 회원을 추천하는 시스템을 구현하였습니다.

새로 팔로우 할 회원은 팔로우하는 관계가 가까운 순서로 추천합니다.

BFS 알고리즘을 통해 팔로우 하고 있는 회원을 순회하여 자동으로 거리 기반 순서로 추천할 수 있도록 구현하였습니다.

처음 큐에 팔로우하고 있는 회원을 넣고, 그 회원이 팔로우하는 회원들을 BFS로 탐색합니다.
회원은 회원 Id로 식별하기 때문에, 이미 방문한 회원은 `Set`에 저장하여 중복 방문을 방지합니다.

총 10명의 회원을 추천하며, 추천 목록이 10명 채워졌을 경우 추천을 종료합니다.

```java
final int NUM_RECOMMENDATIONS = 10;

List<String> followings = repo.getFollowings(userId);
List<String> recommendedUsers = new ArrayList<>();

// BFS
Queue<String> q = new ArrayDeque<>();
Set<String> visited = new HashSet<>();
visited.add(userId);
for (String followedUser : followings) {
    visited.add(followedUser);
    q.add(followedUser);
}

while (!q.isEmpty()) {
    String next = q.poll();
    List<String> nextFollowings = repo.getFollowings(next);

    for (String candidate : nextFollowings) {
        if (!visited.contains(candidate)) {
            visited.add(candidate);
            q.add(candidate);

            recommendedUsers.add(candidate);
            if (recommendedUsers.size() == NUM_RECOMMENDATIONS) {
                return recommendedUsers;
            }
        }
    }
}
```

추천 목록이 10명이 채워지지 않았을 경우, 전체 회원 목록에서 추천하지 않은 회원을 임의로 선택하여 추천합니다.
전체 회원 목록을 순서대로 순회하며 추천 목록에 추가합니다.

```java
// 추천 유저가 부족할경우 임의 유저 추천
if (recommendedUsers.size() < NUM_RECOMMENDATIONS) {
    List<User> allUsers = repo.selectAll();
    for (User user : allUsers) {
        if (!visited.contains(user.getUserId())) {

            recommendedUsers.add(user.getUserId());
            if (recommendedUsers.size() == NUM_RECOMMENDATIONS) {
                return recommendedUsers;
            }
        }
    }
}
```

순서대로 순회하기 때문에 추천 목록은 완전히 랜덤으로 추천되지는 않습니다.
향후 회원의 관심사나 행동 패턴, 혹은 회원의 인기 척도 등을 고려하여 추천할 수 있도록 개선할 수 있습니다.

### JSP

총 10명의 회원을 추천하도록 설계했으나, 회원이 모든 사용자를 팔로우하고 있는 경우 추천할 사용자가 부족할 수 있습니다.

이에 추천 사용자가 없는 경우 표시를 따로 추가하였습니다.

추천 사용자는 사용자 아이디와 함께 팔로우 버튼을 함께 표시합니다.

향후 프론트엔드 고도화 시 사용자 개인 정보 페이지나 프로필 정보 등을 표시할 수 있도록 개선할 수 있습니다.

```jsp
<jsp:include page="/common/header.jsp" />
<h2>아래 사용자들을 추천합니다.</h2>

<c:if test="${empty recommendedUsers}">
  <p>추천할 사용자가 없습니다.</p>
</c:if>

<c:forEach var="userId" items="${recommendedUsers}">
  <p><c:out value="${userId}"/></p>
  <jsp:include page="/common/followButton.jsp">
    <jsp:param name="targetId" value="${userId}"/>
  </jsp:include>
</c:forEach>

<jsp:include page="/common/footer.jsp" />
```

## 느낀점

알고리즘 적용을 위해 사이트에 필요한 기능을 기획하는 과정이 재밌었습니다.

당장 구현 가능하면서 사이트에 필요한 기능을 조원과 오랜 시간 논의하여 결정하였습니다.
사용자가 SSAFIT 사이트를 어떻게 쓸 지, 다른 비슷한 사이트에서는 어떤 기능이 있고 어떻게 구현했을 지 등을 얘기하며 기능을 기획했습니다.

기존에 작성한 백엔드 코드가 있었기 때문에 기능 기획 시간 대비 구현 시간은 짧게 소요되었으며 산뜻하고 수월한 팀 워크가 가능했습니다.


