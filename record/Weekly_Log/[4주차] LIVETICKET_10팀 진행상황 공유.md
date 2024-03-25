# **[4주차] Project_10팀 진행상황 공유** (4)

## 팀 구성원, 개인 별 역할

---

- 팀원 : 윤가영, 권경현, 안우성, 이예원, 최도영
- 팀원 전체 : 주제 선정, DB설계

### 윤가영

- 피그마 작성 , 검색 기능 구현 ,  결제 페이지 및 기능 구현 , 개인정보 수정
  - 개인정보 수정 기능 구현

### 안우성

- 회원가입 기능 구현 ,  로그인 기능 구현 , 마이페이지 기능 구현 , 채팅 기능 구현
  - 회원가입 , 로그인 기능 구현
  - 카카오 로그인 기능 구현
  - 채팅 기능 구현

### 최도영

- ERD 작성 , 결제 페이지 및 기능 구현, 티켓 상태 변경
  - 결제 기능 구현

### 권경현

- 기획서 작성 , 고객센터 페이지 및 기능 구현 , 기대평 기능구현 , 배포
  - 고객센터 페이지 구현

### 이예원

- 플로우 차트 작성 , 메인페이지 제작 , 관리자페이지 기능 구현
  - 콘서트 글 등록 기능 구현

## 팀 내부 회의 진행 회차 및 일자

- 13회차 디스코드 음성채팅 회의 진행
- 14회차 디스코드 음성채팅 회의 진행
- 15회차 디스코드 음성채팅 회의 진행
- 16회차 디스코드 음성채팅 회의 진행
- 일주일간 진행횟수 : 4회

## 현재까지 개발 과정 요약

### 윤가영

- 콘서트 검색 기능

### 안우성

- 회원가입 , 카카오 로그인 기능 구현
- 회원탈퇴(카카오 외부 서비스 회원탈퇴 포함)
- 레드판다 → RabbitMq로 채팅 기능 수정

### 최도영

- 주문 로직 리팩토링
- 대기열 기능 구현

### 권경현

- 고객센터 페이지 페이지 및 기능 구현
- 상세 페이지 리뷰 페이징 구현
- 고객센터 페이지 페이징 구현
- 상세페이지 지도구현중

### 이예원

- 게시글 카카오 지도 삽입
- 게시글 이미지 파일 첨부 구현 중

## 개발 과정에서 나왔던 질문

### 콘서트 등록할때 좌석수 만큼 좌석 생성되지 않은 문제

일단 임시로 그냥 좌석 수 만큼만 받아서 좌석 생성하기

### join fetch 대신 left join fetch 를 사용해야하는 이유?

```java

@Query("SELECT c FROM Concert c join fetch c.place p left join fetch c.concertPerformer cp " +
            "WHERE LOWER(c.concertNameKr) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(cp.artistNameEng) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(cp.artistNameKr) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(c.concertNameEng) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(p.placeName) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(c.category) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "AND c.status = 'ENABLE'")
    List<Concert> findByConcertKeyword(@Param("keyword") String keyword);
```

**`concertPerformer`**는 **`Concert`** 엔티티와 연관된 엔티티로  OneToOne 관계라 join fetch 를 쓰는게 맞다. 하지만 더미데이터에 null 값으로 되어있고  inner join사용하면 null 들어가있는 값들은 가져올수가없어서  left join null로 되어있는 값을 가져온다 → 추후 리펙토링 할 필요가 있음

## 개발 결과물 공유

Github Repository URL:

[live-ticket](https://github.com/live-ticket)

- 필수) 팀원들과 함께 찍은 인증샷(온라인 만남시 스크린 캡쳐)도 함께 업로드 해주세요 🙂