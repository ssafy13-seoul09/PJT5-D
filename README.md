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
PJT4-B
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
│   ├── main
│   │   ├── java
│   │   ├── webapp
│   │   │   ├── META-INF
│   │   │   ├── WEB-INF
│   │   │   │   ├── review
│   │   │   │   │   ├── detail.jsp
│   │   │   │   │   ├── register.jsp
│   │   │   │   │   └── update.jsp
│   │   │   │   ├── user
│   │   │   │   │   ├── favorite.jsp
│   │   │   │   │   ├── following.jsp
│   │   │   │   │   ├── login.jsp
│   │   │   │   │   ├── logout.jsp
│   │   │   │   │   ├── mypage.jsp
│   │   │   │   │   └── register.jsp
│   │   │   │   ├── video
│   │   │   │   │   ├── reviewList.jsp
│   │   │   │   │   ├── showAllVideos.jsp
│   │   │   │   │   └── videoDetail.jsp
│   │   │   │   ├── index.jsp
│   │   │   │   ├── test.jsp
│   │   │   │   ├── userTest.jsp
│   │   │   │   └── videoDetail.jsp

```
## ERD Table
![ERDiagram](https://github.com/user-attachments/assets/41893808-5a59-4587-8adf-17815972a818)



## 사용 예시 (TBA)
