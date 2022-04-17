package com.example.springboot_example.dto;


import com.example.springboot_example.Entity.Article;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;


@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
@Getter
public class ArticleForm {

    private Long id;
    private String title;
    private String content;

    //dto를 Entity로
    public Article toEntity(){
        return new Article(id, title, content);
    }
}
