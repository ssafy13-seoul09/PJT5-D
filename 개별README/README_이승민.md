## 역할

- 회원 관련 기능 구현
- DB 설계 총괄 

## 구현 사항

- User MVC 패턴 구현
  - Model
    - User DTO
    - User Repository
      - 메모리 기반 Repository에서 JDBC 기반 MySQL Repository로 변경
    - User Service
  - View: JSP 기반
    - 회원 기본
      - 회원 로그인/로그아웃
      - 회원 가입
    - 마이페이지
      - 팔로우 하고 있는 회원 목록
      - 좋아요 누른 영상 목록
  - Controller: Servlet 기반 Front Controller 패턴
    - 회원 기본
      - 회원 로그인/로그아웃
      - 회원 가입
    - 마이페이지
      - 회원 팔로우/언팔로우
      - 영상 좋아요/좋아요 취소

## 느낀점





