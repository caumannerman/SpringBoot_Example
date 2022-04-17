package com.example.springboot_example.Entity;

import lombok.*;

import javax.persistence.*;

@Entity // DB가 해당 객체를 인식 가능! ( 테이블을 만듦)
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
@Getter
public class Article {

    @Id // 대표값을 지정!
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 자동 생성 어노테이션
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {

        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
    }
}
