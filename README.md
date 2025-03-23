# PJT 소개 
- 운동 관련 영상 업로드 및 리뷰 작성 프로그램
- 개발 언어: Java, JSP, MySQL
- 조원: 이아영, 이재석, 이승민 

## 팀원 및 역할 분배
- 이아영: Review 기능 구현
- 이재석: Video 기능 구현
- 이승민: User 기능 구현 및 DB 생성

## 프로젝트 구조 
```
PJT4-B [PJT4-B master]
│── Deployment Descriptor: PJT4-B
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

