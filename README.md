## 1. 목표
Facebook Feed처럼 글을 올리고, 그 글에 댓글과 좋아요를 눌러 사용자간 소통이 가능한 소셜피드 서비스 구현

## 2. 기능
- [User] 로그인, 회원가입 API
- [User] 사용자 인증 API
    - 사용자권한
        - 모든 사용자: 글, 댓글, 좋아요 읽기, 로그인, 회원가입
        - 가입한 사용자: 글 쓰기/수정/삭제, 댓글 쓰기/수정/삭제, 좋아요 하기/취소
- [Feed] 모든 컨텐츠(게시물, 댓글, 좋아요 수 등) 가져오기 API
- [Feed] 글 CRUD API
- [Feed] 댓글 CRUD API
- [Feed] 좋아요 CRUD API

## 3. API 문서 [보러가기](https://documenter.getpostman.com/view/8568933/TzedhQh6)

## 4. DB
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

## 5. 프로젝트 흐름
<img src = "docs/images/request_flow.png">

## 6. 문제해결 전략
- 메모리 사용량
    - `Controller`, `Service`, `Repository`를 싱글톤으로 만든이유
        - 각 요청마다 다른 객체의 상태를 가지고 않기 때문에 싱글톤이 효율적입니다.
        - 여러 요청이 한가지 공유 자원에 접근할 경우 동기화하기 쉽습니다.
- Thread-safety
    - `Repository`에서 읽기만 하는 곳은 `syncronized` 키워드를 붙이지 않은 이유
        - 여러 요청이 동시에 접근해도 동일한 데이터를 읽어가기 때문에 동기화의 필요성이 약하다고 느꼈습니다. 
    - `Service`에 `syncronized` 키워드를 붙였으면서 왜 또 `Repository`에도 붙였나
        - `Service`에 동기화 처리를 해주었다고 해도 여러 `Service`에서  같은 `Repository`의 공유자원을 변경할 메서드에 접근할 수도 있습니다. 
    - DB를 `ConcurrentHashMap`으로 설정한 이유
        - 프레임워크를 사용하지 않고 간단한 데이터를 저장하기에 가장 효율적이라고 생각했습니다.
        - 관계형 데이터베이스는 찾아와야하는 유니크한 key가 있고 key로 찾으면 반환되는 데이터가 Map 형식과 가장 유사하다고 생각했습니다.
        - `ConcurrentHashMap`은 서로 다른 스레드가 하나의 해시 노드(Map 요소)에 접근할 경우 `synchronized` 블록을 사용해 동기화를 해줍니다.
        
- MVC 패턴
    - `FrontControllerSerlvet`을 사용한 이유
        - Servlet을 여러개 만들어서 URL 매핑할 수도 있겠지만 FrontController를 사용하여 직접 필요한 handler를 매핑해주면 
        모든 Controller의 의존관계와 집입되는 경로(url) 등이 한눈에 보여 애플리케이션이 더 이해가 잘됩니다.
        
- 사용자 인증
    - 사용자 인증에 `filter`를 사용한 이유
        - Filter는 모든 요청마다 Servlet.service()가 실행되기 전에 거쳐가는 곳이기 때문에 사용자 인증을 하기 좋은 구간입니다.
    - 사용자 세션 유지를 어떻게 했는지
        - SessionUserService.java 를 만들어 모든 세션은 한곳에서 추가, 삭제, 찾아오기가 가능하도록 했습니다. 
        이렇게 한 이유는 Session key값을 Session 사용하는 곳에서 지정할 경우 유지보수가 힘들기 때문입니다.
- OOP
    - `AppConfig.java` 파일을 통해서 구현객체를 주입한 이유
        - 한곳에서 의존성을 주입받으면 객체간 결합도를 낮출 수 있습니다. 결합도를 낮추면 애플리케이션 확장성이 좋아집니다.
    - `RequestDTO`에 `setter`를 추가한 이유
        - `Request body`에서 `RequestDto`로 타입 변환을 할 때 jackson 라이브러리의 `ObjectMapper().readValue()` 기능을 사용합니다. 이 메서드에서 setter를 통해서 값을 주입하여 반환해줍니다.
    - 다른 `domain`, `dto` 객체에는 추가하지 않은 이유
        - `setter`는 정말 필요한 상황이 아니라면 되도록 만들지 않는 것이 좋습니다. 이유는 여러 곳에서 `setter`를 사용하여 데이터 값이 변하면 쉽게 예측할 수 없기 때문입니다.

## 7. Tomcat 설정 및 빌드
- [페이지 이동](https://github.com/olivejua/feed-app/wiki/Tomcat-%EC%84%A4%EC%A0%95-%EB%B0%8F-%EB%B9%8C%EB%93%9C)
