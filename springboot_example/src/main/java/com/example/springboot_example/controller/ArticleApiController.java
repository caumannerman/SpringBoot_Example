package com.example.springboot_example.controller;


import com.example.springboot_example.Entity.Article;
import com.example.springboot_example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


}
