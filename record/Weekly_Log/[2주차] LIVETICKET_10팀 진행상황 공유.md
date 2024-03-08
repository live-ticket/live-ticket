# **[2주차] Project_10팀 진행상황 공유**

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

- 5회차 디스코드 음성채팅 회의 진행
- 6회차 디스코드 음성채팅 회의 진행
- 7회차 디스코드 음성채팅 회의 진행
- 8회차 디스코드 음성채팅 회의 진행
- 일주일간 진행횟수 : 4회

## 현재까지 개발 과정 요약

- 패키지 구조 생성
  - 수업 내용으로 바탕으로 도메인 별로 패키지 구조 생성
- 공용 설정 생성
  - application.yml, build.gradle, Spring Security, SecuritConfig 공용 설정
- Git 관리 방식
  - 개인의 브랜치에서 작업 이후 dev 파일에 pull
- 개발 중 설계 부분에 문제가 발생하면 해당 부분의 담담자와 논의 후 담당자가 수정 (*담당자 이외에는 수정 불가)
- 질문이나 막히는 부분이 있으면 팀원과 공유
- 운영시간 이외에 부족한 부분은 추가적인 개인이 개발 시간 가짐



## 개발 과정에서 나왔던 질문

### Q1. 티켓 좌석 등급 별로 가격을 다르게 할 것 인가?

- 좌석 가격 동일 시 결정
- 좌석 등급 별로 가격을 설정하는 것은 단순한 계산이 아니라, 다양한 비즈니스 규칙과 기술적 요구 사항을 반영하는 복잡한 프로세스를 개발자가 생각해야하는 것

### Q2. 암표를 해결하기 위해서 어떤 방법을 이용할 것인가?

- 메크로를 막기위해서 Recaptcha를 사용
- Recaptcha는 Google에서 개발한 자동화된 봇 공격을 방지하기 위한 강력한 보안 도구
- 사용자가 봇인지 아닌지를 판단 후 개발자는 이 점수를 기반으로 액세스를 허용하거나 차단한다.

### Q3.

```java
<동작되지않았던 코드>

@PostMapping("/join")
public String postJoinForm(@Valid @ModelAttribute JoinRequest joinRequest,
                           Model model,
                           BindingResult bindingResult) {
  
}

<수정한 코드>

@PostMapping("/join")
    public String postJoinForm(@Valid @ModelAttribute JoinRequest joinRequest,
                               BindingResult bindingResult,
                               Model model) {  // 순서 중요 !! 

        if (bindingResult.hasErrors()) {
            return "domain/member/joinForm";
        }

        try {
            memberService.join(joinRequest);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("signupFormDto", joinRequest);
            return "domain/member/joinForm";
        }

        return "redirect:/members/login";
    }
```

- Spring 에서 @ModleAttribute로 주어진 객체는 컨트롤러 메서드의 매개변수로 초기화 되기전에 초기화를 시켜야함
- @ModleAttribute 사용하는 경우 Model 객체가 먼저 초기화 되어야함

## 개발 결과물 공유

Github Repository URL:

[live-ticket](https://github.com/live-ticket)

- 필수) 팀원들과 함께 찍은 인증샷(온라인 만남시 스크린 캡쳐)도 함께 업로드 해주세요 🙂
- # **[2주차] Project_10팀 진행상황 공유**

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

- 5회차 디스코드 음성채팅 회의 진행
- 6회차 디스코드 음성채팅 회의 진행
- 7회차 디스코드 음성채팅 회의 진행
- 8회차 디스코드 음성채팅 회의 진행
- 일주일간 진행횟수 : 4회

## 현재까지 개발 과정 요약

- 패키지 구조 생성
  - 수업 내용으로 바탕으로 도메인 별로 패키지 구조 생성
- 공용 설정 생성
  - application.yml, build.gradle, Spring Security, SecuritConfig 공용 설정
- Git 관리 방식
  - 개인의 브랜치에서 작업 이후 dev 파일에 pull
- 개발 중 설계 부분에 문제가 발생하면 해당 부분의 담담자와 논의 후 담당자가 수정 (*담당자 이외에는 수정 불가)
- 질문이나 막히는 부분이 있으면 팀원과 공유
- 운영시간 이외에 부족한 부분은 추가적인 개인이 개발 시간 가짐



## 개발 과정에서 나왔던 질문

### Q1. 티켓 좌석 등급 별로 가격을 다르게 할 것 인가?

- 좌석 가격 동일 시 결정
- 좌석 등급 별로 가격을 설정하는 것은 단순한 계산이 아니라, 다양한 비즈니스 규칙과 기술적 요구 사항을 반영하는 복잡한 프로세스를 개발자가 생각해야하는 것

### Q2. 암표를 해결하기 위해서 어떤 방법을 이용할 것인가?

- 메크로를 막기위해서 Recaptcha를 사용
- Recaptcha는 Google에서 개발한 자동화된 봇 공격을 방지하기 위한 강력한 보안 도구
- 사용자가 봇인지 아닌지를 판단 후 개발자는 이 점수를 기반으로 액세스를 허용하거나 차단한다.

### Q3.

```java
<동작되지않았던 코드>

@PostMapping("/join")
public String postJoinForm(@Valid @ModelAttribute JoinRequest joinRequest,
                           Model model,
                           BindingResult bindingResult) {
  
}

<수정한 코드>

@PostMapping("/join")
    public String postJoinForm(@Valid @ModelAttribute JoinRequest joinRequest,
                               BindingResult bindingResult,
                               Model model) {  // 순서 중요 !! 

        if (bindingResult.hasErrors()) {
            return "domain/member/joinForm";
        }

        try {
            memberService.join(joinRequest);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("signupFormDto", joinRequest);
            return "domain/member/joinForm";
        }

        return "redirect:/members/login";
    }
```

- Spring 에서 @ModleAttribute로 주어진 객체는 컨트롤러 메서드의 매개변수로 초기화 되기전에 초기화를 시켜야함
- @ModleAttribute 사용하는 경우 Model 객체가 먼저 초기화 되어야함

## 개발 결과물 공유

Github Repository URL:

[live-ticket](https://github.com/live-ticket)

- 필수) 팀원들과 함께 찍은 인증샷(온라인 만남시 스크린 캡쳐)도 함께 업로드 해주세요 🙂
- ![10팀.png](..%2F..%2F..%2F..%2F..%2FDownloads%2F10%ED%8C%80.png)