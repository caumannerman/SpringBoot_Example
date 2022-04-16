package com.example.springboot_example.controller;

import com.example.springboot_example.Entity.Article;
import com.example.springboot_example.dto.ArticleForm;
import com.example.springboot_example.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {

    @Autowired // 스프링부트가 미리 생성해놓은 객체를 가져다 자동 연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);

        //1: id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //2: 가져온 데이터를 모델에 등록 ( view template에서 사용하기 위해)
        model.addAttribute("article", articleEntity);

        //3: 보여줄 페이지를 설정!
        return "articles/show";

    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){

        // Dto를 변환 (Entity)
        Article article = form.toEntity();
        System.out.println(article.toString());

        // Repository에게 Entity를 DB안에 저장하게 함 !
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }


}
