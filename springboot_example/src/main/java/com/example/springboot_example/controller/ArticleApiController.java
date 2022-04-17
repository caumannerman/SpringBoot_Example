package com.example.springboot_example.controller;


import com.example.springboot_example.Entity.Article;
import com.example.springboot_example.dto.ArticleForm;
import com.example.springboot_example.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // RestAPI용 컨트롤러! 데이터(Json)을 반환
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article getOneArticle(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto){

        //Request의 Body에서 받아온 json이 dto로 맵핑되고,우리가 Entity로 바꾸어준다. ( id도 body에 같이 들어옴)
        Article article = dto.toEntity();
        log.info("mymy", id, article.toString());

        // 대상 엔티티를 조회
        Article former = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if (former == null || id != article.getId()){
            // 400 응답
            log.info("잘못된 요청!! id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        //업데이트 및 정상  응답
        // 요청의 body로 받은 dto에 title, 혹은 content가 비어서 왔을 경우에 기존에 있던 title/content를 유지시켜야하므로 Article클래스에 patch 메서드 작성
        former.patch(article);
        Article updated = articleRepository.save(former);
        return ResponseEntity.status(HttpStatus.OK).body(updated);


    }


}
