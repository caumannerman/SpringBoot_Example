package com.example.springboot_example.Entity;


import lombok.*;

import javax.persistence.*;

@Entity //새로운 댓글 테이블이 만들어진다!!
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 해당 댓글 엔티티 여러개가 하나의 Article에 연관
    @JoinColumn(name = "article_id")  // article_id라는 이름으로 Column이 생성된다.
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;
}
