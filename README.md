# SpringBoot_Example

SpringBoot 개념을 잡기 위한 토이 프로젝트
Article(게시글) dto, Entity, Repository (JPA)를 사용하여 h2 DB에 저장 
Comment(댓글)을 Article과 @ManyToOne의 관계로 만들고, 댓글 CRUD 수행 

- 1. mustache view template을 사용하여 api통신을 시각화 하며 테스트. (Controller)
- 2. RestController를 사용하여 json으로 데이터를 주고받도록 Rest API 구현 


> # 1.ArticleController 

Ajax 없이 GetMapping, PostMapping만을 사용하여  게시글 추가, 수정, 삭제, 조회 컨트롤러 작성 

> 게시글 전체 조회
>> Repository를 통해 findAll()메서드 처리 후, 찾아온 Data를 model에 등록 후 view template 뿌려줌.
>> 각 Article의 title에 <a>태그를 걸어, 게시글 상세 페이지로 Link
<img width="1200" alt="스크린샷 2022-04-17 오후 8 30 36" src="https://user-images.githubusercontent.com/75043852/163712482-35efca2f-345a-49d6-a6e2-dd3652d03768.png">

  
> 게시글 detail
>> edit, delete 버튼에 request와, redirect를 설정하여, 수정 혹은 삭제 후 지정된 View template과 데이터를 뿌려줌 
<img width="1200" alt="스크린샷 2022-04-17 오후 8 34 07" src="https://user-images.githubusercontent.com/75043852/163712593-46b77db1-5089-4fc6-b850-9dbeb349a0dc.png">

> 게시글 Edit
>> edit페이지 요청시, @PathVariable로 전달받은 게시글의 id로 ArticleRepository가 해당 게시글을 DB에서 찾아와, 페이지에 뿌려준다.
  
  <img width="600" alt="스크린샷 2022-04-17 오후 8 39 08" src="https://user-images.githubusercontent.com/75043852/163712772-eb80cef2-40f1-4f29-b608-437d3681cdb9.png">

<img width="1509" alt="스크린샷 2022-04-17 오후 8 37 58" src="https://user-images.githubusercontent.com/75043852/163712728-a961a90b-4fd0-4ebb-8afa-74c4fad3b15b.png">

> 게시글 Delete
>> 게시글 삭제 후, articles 목록 페이지로 redirect
  <img width="1507" alt="스크린샷 2022-04-17 오후 8 41 12" src="https://user-images.githubusercontent.com/75043852/163712857-fa6865ca-fc1a-40e6-87f5-d83a3ad2176d.png">

  
> 게시글 생성
>> dto로 Entity를 생성하고, Repository를 통해 DB에 저정한 뒤, 게시글 페이지로 redirect한다.
  
<img width="1505" alt="스크린샷 2022-04-17 오후 8 45 20" src="https://user-images.githubusercontent.com/75043852/163713010-2aac259c-2d87-417a-9b0b-c0047d1d6c82.png">

> # 2. ApiController

> 서비스와 트랜잭션 

>> - 1. 서비스 :Controller와 Repository 사이에 존재하는 계층, 처리 업무의 순서를 총괄한다. => Controller는 요청을 받고, 응답을 처리하는 것에만 집중 가능 ! 서비스는 업무 처리 흐름과 트랜잭션 관리 가능! (Rollback) 
>> - 2. 트랜잭션 : 모두 성공되어야하는 일련의 과정. 중간에 실패 시, 시작 전 상태로 Rollback 

### ArticleApiController는 request를 받아, @PathVariable, @RequestBody에서 받아온 parameter들을 Service의 메서드로 전달해주고, ResponseEntity<Article>을 결과값으로 return해주는 역할만 담당.
  
### ArticleService 에서 각 method들이 업무 처리, 트랜잭션 관리 등 수행하고, DB에서 처리된 결과 Entity를 return해준다.
>>> ViewTemplate을 이용한 Controller에 구현된 것과 같은 기능이 모두 구현되었다.

  
> # 3. Text코드 작성 
  성공, 실패의 경우로 나누어 Service에 작성한 메서드들에 대하여 아래와 같이 테스트 코드를 작성하고 실행해보았다.
  여러 Test를 동시에 수행할 경우, DB가 변경되어 뒷 쪽 Test에서 예상과 다른 결과가 나오는 것을 방지하기 위해 @Transactional을 적용해주었다.
  <img width="511" alt="스크린샷 2022-04-19 오후 4 47 20" src="https://user-images.githubusercontent.com/75043852/163952487-a143773f-7661-4a86-a5cf-2b5b363d2724.png">

> # 4. 댓글 ( Comment )테이블 생성 ( 게시글과 1:N 관계 )
CrudRepository를 확장한 PagingAndSortingRepository를 또 한 번 확장한 JpaRepository로 CommentRepository를 구성하였다.
 - 1. JpaRepository는 CRUD기능 뿐 아니라 일정 페이지의 조회, Sorting 가능 ...
  주요 annotation @ManyToOne , @JoinColumn, @JsonProperty

> ### 4.1. CommentJpaRepository 구현 내용
  Jpa가 기본적으로 제공해주는 get방식에 없는 기능들은 ( ex. nicmname이 "Yang"인 사용자가 단 댓글 모두 select) 쿼리문을 작성하여 수행해야하는데,  두가지 방식으로 수행하였다.
  - 1. @Query어노테이션을 달아 nativaQuery로 DB에서 쿼리를 수행하여 Entity(혹은 Entity들)을 받아오는 방식
  
  <img width="650" alt="스크린샷 2022-04-19 오후 6 31 55" src="https://user-images.githubusercontent.com/75043852/163974508-8d104281-160b-4f54-8173-3ee1e4cefdd2.png">

  - 1. resources/META-INF/orm.xml 파일에서 <named-native-query>의 name의 메서드 이름과 <query>를 연결해주고, 해당 메서드 이름은 이렇게 연결된 쿼리를 수행하고, result-class에 지정된 클래스로 결과를 반환하도록 지정해주는 방식
  
  <img width="650" alt="스크린샷 2022-04-19 오후 6 31 26" src="https://user-images.githubusercontent.com/75043852/163974416-b10f9036-0333-496f-8f09-941e93748553.png">
  
> ### 4.2 @DataJpaTest와 @SpringBootTest 비교
  
  댓글Entity에 사용하기 위해, Comment Repository를 JpaRepository로 구현하였다.
 
>> @DataJpaTest 
  - 1. 오직 Jpa 컴포넌트들만을 테스트하기 위한 어노테이션.
  - 1. full-auto config를 해제하고 JPA테스트와 연관된 config만 적용 
>> @SpringBootTest
  - 1. full application config를 로드해서 통합 테스트를 진행 하기 위한 annotation이다. 
  - 1. 상대적으로 무겁다.
  - 1. 테스트마다 DB가 롤백디지 않기 때문에 @Transactional을 추가로 달아주어야한다!
  
> # 5. 댓글 표시, 작성 UI 구성, JS와 fetch()를 사용한 REST API 호출 
  
  - 1. bootstrap을 사용하여 댓글 UI 구성 
  - 1. querySelector, addEventListener, fetch를 사용한 CRUD를 JavaScript로 수행 
  
  <img width="700" alt="스크린샷 2022-04-21 오후 4 09 05" src="https://user-images.githubusercontent.com/75043852/164395246-95df4496-290b-4bb6-85e3-2ea22741a024.png">

