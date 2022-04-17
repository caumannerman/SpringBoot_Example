package com.example.springboot_example.service;

import com.example.springboot_example.Entity.Article;
import com.example.springboot_example.dto.ArticleForm;
import com.example.springboot_example.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 서비스 선언 (서비스 객체를 스프링부트에 생성)
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getOneArticle(Long id) {
        return articleRepository.findById(id).orElse(null);
    }


    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if ( article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {

        //Request의 Body에서 받아온 json이 dto로 맵핑되고,우리가 Entity로 바꾸어준다. ( id도 body에 같이 들어옴)
        Article article = dto.toEntity();
        log.info("mymy", id, article.toString());

        // 대상 엔티티를 조회
        Article former = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if (former == null || id != article.getId()){

            log.info("잘못된 요청!! id: {}, article: {}", id, article.toString());
            return null;
        }

        //업데이트 및 정상
        // 요청의 body로 받은 dto에 title, 혹은 content가 비어서 왔을 경우에 기존에 있던 title/content를 유지시켜야하므로 Article클래스에 patch 메서드 작성
        former.patch(article);
        Article updated = articleRepository.save(former);
        return updated;

    }

    public Article delete(Long id) {
        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        //없는 경우 잘못된 요청 처리
        if (target == null) {
            return null;
        }

        //대상 삭제
        articleRepository.delete(target);

        //데이터 반환
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //dto묶음을 entity 묶음ㅇ로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        //entity 묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        //강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패!!:")
        );

        //결과값 반환
        return articleList;

    }
}
