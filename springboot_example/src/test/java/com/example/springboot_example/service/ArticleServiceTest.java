package com.example.springboot_example.service;

import com.example.springboot_example.Entity.Article;
import com.example.springboot_example.dto.ArticleForm;
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

    @Test
    void getOneArticle_성공____존재하는_id_입력() {
        Long id = 1L;
        //예상
        Article expected = new Article(id, "가가가가", "1111");

        //실제
        Article article = articleService.getOneArticle(id);
        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void getOneArticle_실패____존재하지않는_id_입력() {

        //존재하지 않는 id
        Long id = -1L;

        //예상
        Article expected = null; // id를 찾지 못하면 우리는 null을 반환화도록 작성해놓았으므로.

        //실제
        Article article = articleService.getOneArticle(id);
        //비교
        assertEquals(expected, article);
    }

    @Test
    void create_성공____title과content만_있는_dto_입력() {

        Long id = -1L;

        //예상
        String title = "";
        String content = "";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);


        //실제
        Article article = articleService.create(dto);
        //비교
        assertEquals(expected.toString(), article.toString());
    }

}