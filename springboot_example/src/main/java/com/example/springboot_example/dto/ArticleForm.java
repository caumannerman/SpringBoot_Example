package com.example.springboot_example.dto;


import com.example.springboot_example.Entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title;
    private String content;





    //dto를 Entity로
    public Article toEntity(){
        return new Article(null, title, content);
    }
}
