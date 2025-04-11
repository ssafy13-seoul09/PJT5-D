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
![image](https://github.com/user-attachments/assets/099ed96f-c482-44d1-8bd6-cc7af1e04f8f)
- 팔로우 추천 기능

![image](https://github.com/user-attachments/assets/64012217-6aef-455b-ae00-3a273498c1db)
- 영상 검색 기능




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

**요약:**  
사용자가 입력하는 검색어와 일치하는 문자열을 가진 영상 목록을 제공합니다.  
전체 영상 제목과 완전히 일치하는 검색어가 아니더라도 영상 제목과 패턴이 일치하면 해당 제목의 영상들을 사용자에게 제공합니다.

**상세 내용:**  
사용자에게 입력받은 검색어를 전체 영상제목과 비교하여 일치하는 패턴을 가진 영상 제목을 출력합니다.  
영문의 경우 대/소문자에 따른 차이가 있기 때문에 사용자에게 입력받은 키워드와 영상 제목 DB 모두 소문자로 변환 후 KMP 알고리즘을 통해 패턴을 비교했습니다.  

**이점:**  
- KMP 알고리즘을 활용하여 입력받은 검색어와 영상 제목의 패턴을 비교합니다.
- 브루트포스 방식과 비교했을 때, 
- 사용자는 관심있는 키워드와 관련된 영상을 검색할 수 있게 되어, 필요한 운동 영상을 원활히 시청할 수 있습니다.  

**이슈 사항:**  
- 한글에 대한 처리가 잘 될지에 대한 걱정이 있었지만, 한글 처리에는 문제 없었습니다.
- 영문의 경우 같은 문자여도 대문자, 소문자에 대한 차이가 있어서 알고리즘을 통해 비교하기 전에 모두 소문자로 바꿔준 후, KMP 알고리즘을 통해 패턴을 비교했습니다.  

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

