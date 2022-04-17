# SpringBoot_Example

SpringBoot 개념을 잡기 위한 토이 프로젝트
Article(게시글) dto, Entity, Repository (JPA)를 사용하여 h2 DB에 저장 

- 1. mustache view template을 사용하여 api통신을 시각화 하며 테스트. (Controller)
- 2. RestController를 사용하여 json으로 데이터를 주고받도록 Rest API 구현 


> # 1.ArticleController 

Ajax 없이 GetMapping, PostMapping만들 사용하여  게시글 추가, 수정, 삭제, 조회 컨트롤러 작성 

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
elete
  
> 게시글 생성
>> dto로 Entity를 생성하고, Repository를 통해 DB에 저정한 뒤, 게시글 페이지로 redirect한다.
  
<img width="1505" alt="스크린샷 2022-04-17 오후 8 45 20" src="https://user-images.githubusercontent.com/75043852/163713010-2aac259c-2d87-417a-9b0b-c0047d1d6c82.png">

> # 2. ApiController

> 서비스와 트랜잭션 

>> - 1. 서비스 :Controller와 Repository 사이에 존재하는 계층, 처리 업무의 순서를 총괄한다. => Controller는 요청을 받고, 응답을 처리하는 것에만 집중 가능 ! 서비스는 업무 처리 흐름과 트랜잭션 관리 가능! (Rollback) 
>> - 2. 트랜잭션 : 모두 성공되어야하는 일련의 과정. 중간에 실패 시, 시작 전 상태로 Rollback 

### ArticleApiController는 request를 받아, @PathVariable, @RequestBody에서 받아온 parameter들을 Service의 메서드로 전달해주고, ResponseEntity<Article>을 결과값으로 return해주는 역할만 담당.
  
### ArticleService 에서 각 method들이 업무 처리, 트랜잭션 관리 등 수행하고, DB에서 처리된 결과 Entity를 return해준다.
>>> ViewTemplate을 이용한 Controller와 같은 기능이 모두 구현되었다.
