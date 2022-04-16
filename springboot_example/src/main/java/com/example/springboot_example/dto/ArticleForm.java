package com.example.springboot_example.dto;


import com.example.springboot_example.Entity.Article;

public class ArticleForm {
    private String title;
    private String content;

    public ArticleForm(String title, String content){
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    //dto를 Entity로
    public Article toEntity(){
        return new Article(null, title, content);
    }
}
