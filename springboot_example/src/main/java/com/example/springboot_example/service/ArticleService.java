package com.example.springboot_example.service;

import com.example.springboot_example.Entity.Article;
import com.example.springboot_example.dto.ArticleForm;
import com.example.springboot_example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
}
