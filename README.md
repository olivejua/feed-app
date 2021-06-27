## 목표
Facebook Feed처럼 글을 올리고, 그 글에 댓글과 좋아요를 눌러 사용자간 소통이 가능한 소셜피드 서비스 구현

## 기능
- [User] 로그인, 회원가입 API
- [User] 사용자 인증 API
    - 사용자권한
        - 모든 사용자: 글, 댓글, 좋아요 읽기, 로그인, 회원가입
        - 가입한 사용자: 글 쓰기/수정/삭제, 댓글 쓰기/수정/삭제, 좋아요 하기/취소
- [Feed] 모든 컨텐츠(게시물, 댓글, 좋아요 수 등) 가져오기 API
- [Feed] 글 CRUD API
- [Feed] 댓글 CRUD API
- [Feed] 좋아요 CRUD API

## API 문서
[Postman을 이용한 API문서](https://documenter.getpostman.com/view/8568933/TzedhQh6)

## DB
### 설계
<img src = "/docs/images/db_structure.png">

### In-Memory
#### User (사용자)
<img src = "/docs/images/db_user.png">

#### Post (게시물)
<img src = "/docs/images/db_post.png">

#### Comment (댓글)
<img src = "/docs/images/db_comment.png">

#### Like (좋아요)
<img src = "/docs/images/db_like.png">


## 프로젝트 구조
├── README.md </br>
├── src </br>
│   ├── main </br>
│   │   ├── java.com.moneelab.assignment </br>
│   │   │   ├── domain </br>
│   │   │   ├── dto </br>
│   │   │   ├── repository </br>
│   │   │   ├── util </br>
│   │   │   └── web </br>
│   │   │   │   ├── adapter </br>
│   │   │   │   ├── controller </br>
│   ├── resources </br>
│   └── webapp </br>
│   ├── test </br>
│   │   ├── java.com.moneelab.assignment </br>
│   │   └── resources </br>
├── build.gradle </br>
├── gradlew </br>
├── gradlew.bat </br>
└── .gitignore </br>

## 프로젝트 흐름
<img src = "docs/images/request_flow.png">

## 문제해결 전략
- 메모리 사용량
    - 싱글톤으로 만든이유
    - FrontController는 싱글톤으로 만들지 않은 이유
- Thread-safety
    - Repository에서 읽기만 하는 곳은 `syncronized` 키워드를 붙이지 않은 이유
    - Service와 Repository 둘다 `syncronized` 키워드를 붙인 이유
    - DB를 `ConcurrentHashMap`으로 설정한 이유
- MVC 패턴
    - url 매핑 전략
- 사용자 인증
    - 사용자 인증에 `filter`를 사용한 이유
    - 사용자 세션 유지를 어떻게 했는지
- OOP
    - 의존성 관리
    - RequestDTO에 `setter`를 추가한 이유
    - 다른 domain, dto 객체에는 추가하지 않은 이유

## 실행방법
### Tomcat 설정 및 빌드
1. intellij에 프로젝트를 실행하고, 빌드가 잘되는지 확인한다.
    빌드가 안되어 있다면 `Project Structure`에 SDK 설정이 잘 되어 있는지 확인한다.
2. 상단에 `Run` -> `Edit Configurations...` 를 선택한다.
3. 좌측에 `+` 버튼을 클릭하여 `Tomcat Server` -> `Local`을 선택한다.

<img src = "/docs/images/setting-tomcat1.png">
4. Application Server에 tomcat 경로를 선택하고 Name에 이름도 지정해준다.

<img src = "/docs/images/setting-tomcat2.png">
5. 우측 하단에 `Fix` 버튼을 클릭하여 첫번째를 선택한다.

<img src = "/docs/images/setting-tomcat3.png"> 
6. `Application Server`에 경로를 `/`로 변경해준다.

<img src = "/docs/images/setting-tomcat4.png">
7. `OK` 버튼을 누르고 tomcat 실행을 한다.


### Postman 실행

### Test 실행