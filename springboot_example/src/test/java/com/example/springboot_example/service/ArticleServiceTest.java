package com.example.springboot_example.service;

import com.example.springboot_example.Entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest//해당 클래스는 스프링부트와 연동되어 테스팅된
class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Test
    void getAllArticles() {

        //예상
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        //실제
        List<Article> articles = articleService.getAllArticles();

        //비교
        assertEquals(expected.toString(), articles.toString());
    }
}