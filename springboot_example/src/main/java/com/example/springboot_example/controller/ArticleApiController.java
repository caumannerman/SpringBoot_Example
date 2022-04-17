package com.example.springboot_example.controller;


import com.example.springboot_example.Entity.Article;
import com.example.springboot_example.dto.ArticleForm;
import com.example.springboot_example.repository.ArticleRepository;
import com.example.springboot_example.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // RestAPI용 컨트롤러! 데이터(Json)을 반환
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

//    //GET
//    @GetMapping("/api/articles")
//    public List<Article> getAllArticles(){
//        return articleRepository.findAll();
//    }

    //GET
    @GetMapping("/api/articles")
    public List<Article> getAllArticles(){
        return articleService.getAllArticles();
    }

//    @GetMapping("/api/articles/{id}")
//    public Article getOneArticle(@PathVariable Long id){
//        return articleRepository.findById(id).orElse(null);
//    }
//

        @GetMapping("/api/articles/{id}")
    public Article getOneArticle(@PathVariable Long id){
        return articleService.getOneArticle(id);
    }



//    //POST
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto){
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }



    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
//
//    //PATCH
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id,
//                                          @RequestBody ArticleForm dto){
//
//        //Request의 Body에서 받아온 json이 dto로 맵핑되고,우리가 Entity로 바꾸어준다. ( id도 body에 같이 들어옴)
//        Article article = dto.toEntity();
//        log.info("mymy", id, article.toString());
//
//        // 대상 엔티티를 조회
//        Article former = articleRepository.findById(id).orElse(null);
//
//        // 잘못된 요청 처리
//        if (former == null || id != article.getId()){
//            // 400 응답
//            log.info("잘못된 요청!! id: {}, article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        //업데이트 및 정상  응답
//        // 요청의 body로 받은 dto에 title, 혹은 content가 비어서 왔을 경우에 기존에 있던 title/content를 유지시켜야하므로 Article클래스에 patch 메서드 작성
//        former.patch(article);
//        Article updated = articleRepository.save(former);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }



    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto){


        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);


    }
//
//    //delete
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id){
//
//        //대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        //없는 경우 잘못된 요청 처리
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        //대상 삭제
//        articleRepository.delete(target);
//
//        //데이터 반환
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }


    //delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){

        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(deleted):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 트랜잭션 -> 실패 -> 롤백!
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null)?
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
