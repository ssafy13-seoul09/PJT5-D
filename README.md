# PJT 소개 

- 운동 관련 영상 업로드 및 리뷰 작성 프로그램
- 개발 언어: Java, JSP, MySQL
- 조원: 이승민, 주연우

## 팀원 및 역할 분배
- 이승민: BFS를 활용한 팔로우 추천 시스템
- 주연우: KMP를 활용한 영상 검색 시스템

## 프로젝트 구조 
```
PJT5-D [PJT5-D master]
│── Deployment Descriptor: PJT5-D
│── Java Resources
│   └── src/main/java
│       ├── com.ssafy.review.controller
│       │   └── ReviewController.java
│       ├── com.ssafy.review.model.dto
│       │   └── Review.java
│       ├── com.ssafy.review.model.repository
│       │   ├── ReviewRepository.java
│       │   └── ReviewRepositoryImpl.java
│       ├── com.ssafy.review.model.service
│       │   ├── ReviewService.java
│       │   └── ReviewServiceImpl.java
│       ├── com.ssafy.ssafit.controller
│       ├── com.ssafy.user.controller
│       │   └── UserController.java
│       ├── com.ssafy.user.model.dto
│       ├── com.ssafy.user.model.repository
│       ├── com.ssafy.user.model.service
│       ├── com.ssafy.util
│       │   └── DBUtil.java
│       ├── com.ssafy.video.controller
│       │   └── VideoController.java
│       ├── com.ssafy.video.model.dto
│       │   └── Video.java
│       ├── com.ssafy.video.model.repository
│       │   ├── MemoryVideoRepository.java
│       │   ├── VideoRepository.java
│       │   └── VideoRepositoryImpl.java
│       ├── com.ssafy.video.model.service
│       │   ├── VideoService.java
│       │   └── VideoServiceImpl.java
│── resources
│   ├── init.sql
│   └── schema.sql
│── src
│   └── main
│       ├── java
│       ├── resources
│       └── webapp
│           ├── common
│           │   ├── followButton.js
│           │   ├── followButton.jsp
│           │   ├── footer.jsp
│           │   ├── header.jsp
│           │   ├── likeButton.js
│           │   └── likeButton.jsp
│           ├── META-INF
│           ├── WEB-INF
│           │   ├── review
│           │   │   ├── detail.jsp
│           │   │   ├── register.jsp
│           │   │   └── update.jsp
│           │   ├── user
│           │   │   ├── favorite.jsp
│           │   │   ├── following.jsp
│           │   │   ├── login.jsp
│           │   │   ├── mypage.jsp
│           │   │   └── register.jsp
│           │   └── video
│           │       ├── mainPage.jsp
│           │       ├── reviewList.jsp
│           │       └── showAllVideos.jsp
│           └── index.jsp


```
## ERD Table
![ER Diagram](https://github.com/user-attachments/assets/0823ddc2-2459-4938-bca1-9af2e73935c2)

## 사용 예시
![image](https://github.com/user-attachments/assets/3d28542b-53ea-4d9b-a2de-c05788ac2b96)
- welcome page (영상 리스트 출력, 라디오버튼에 따른 필터링 기능 구현)

![image](https://github.com/user-attachments/assets/34e65308-2b61-4e1d-88b1-8d96c787b4e4)
- 비디오 상세보기 (유튜브 썸네일/제목 등 표시, 리뷰 표시)
  
![image](https://github.com/user-attachments/assets/aa10138b-98f8-4203-9803-2b7803463e6f)
- 리뷰 화면
  
![image](https://github.com/user-attachments/assets/2cf679a8-4c9a-4190-83c2-702c07a63dc6)
- 로그인 / 회원가입 기능 구현
  
![image](https://github.com/user-attachments/assets/a7683427-b963-4489-b516-70f0bb34d485)
- 로그인 시 영상 좋아요/좋아요 취소 기능
  
![image](https://github.com/user-attachments/assets/0e9b56b2-adc0-4722-8b78-d86912827d78)
- 로그인 시 댓글을 남긴 다른 사용자에게 팔로우 기능
  
![image](https://github.com/user-attachments/assets/f794aae9-f691-462f-802a-43d0201215b6)
- 본인이 작성한 리뷰는 수정/삭제하는 기능
  
![image](https://github.com/user-attachments/assets/5db7797b-8b17-4f97-b887-9b1d4ea1a1f3)
![image](https://github.com/user-attachments/assets/6613a346-1f7b-4269-852d-062b48ce6b54)
- 마이페이지 내 좋아요/팔로우 기능

## 알고리즘 적용 기획서

| 번호 | 데이터 / 기능 | 자료구조 / 적용 알고리즘 |
| ---- | ------------- | ------------------------ |
| DS-U-01 | 회원 정보       | List|
| DS-U-02 | 회원 팔로우 유저 목록       | List|
| DS-U-03 | 회원 좋아요 영상 목록       | List|
| DS-V-01 | 비디오 목록       | List|
| DS-R-01 | 리뷰 목록       | List|
| Algo-01 | 영상 검색 기능 | KMP |
| Algo-02 | 친구 추천 기능 | BFS |

### Algo-01: 영상 검색 기능

### Algo-02: 팔로우 추천 시스템

**요약:**
- 사용자에게 팔로우할 만한 새로운 사용자를 추천하는 기능

**상세 내용:**
팔로잉 관계 그래프를 활용하여 사용자에게 의미 있는 팔로우 추천을 제공합니다.
사용자가 이미 팔로우하는 사람들을 시작 지점으로, BFS 알고리즘을 통해 사용자와 가까운 관계에 있는 사용자부터 추천합니다.
탐색하는 과정에서 이미 팔로우하고 있는 사용자들은 제외합니다.

시스템은 최대 10명의 사용자를 추천하며, 소셜 그래프 내에서 충분한 추천 후보를 찾지 못한 경우 전체 사용자 목록에서 아직 팔로우하지 못한 사용자를 임의로 선택하여 추천 목록을 완성합니다.

**이점:**
- BFS 알고리즘의 특성을 활용하여 자동으로 거리 기반 우선순위가 적용됩니다.
- 사용자는 자신의 직접 팔로잉보다 한 단계 더 확장된 네트워크를 탐색할 수 있게 되어, 관심 있는 새로운 사용자를 발견할 기회가 늘어납니다.

**이슈 사항:**
- 단순 거리만을 기반으로 추천하기 때문에 사용자의 관심사나 활동 패턴을 고려하지 않습니다.
- 대규모 사용자 환경에서는 BFS 탐색 시 메모리 비용이 높아질 수 있습니다.

