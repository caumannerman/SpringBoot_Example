package com.example.springboot_example.dto;


import com.example.springboot_example.Entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {

    private Long id;
    @JsonProperty("article_id") // Post의 경우 Request의 Body에서 "article_id" 이름으로 들어온 값을 매핑해준다 !
    private Long articleId;
    private String nickname;
    private String body;


    //Entity를 Dto로 바꿔서 return해주는 메서드
    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
