package com.example.springboot_example.service;


import com.example.springboot_example.Entity.Comment;
import com.example.springboot_example.dto.CommentDto;
import com.example.springboot_example.repository.ArticleRepository;
import com.example.springboot_example.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository; // Article도 Comment가 갖고있기 때문에..

    public List<CommentDto> getAllComments(Long articleId) {
        // 조회: 댓글 목록
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // ******** 변환: Entity -> DTO  ********
        // Article과 같이 Entity의 List로 return해줘도 json이 알맞게 날라간다.
        // 하지만, Comment Entity는 Article Entity를 column으로 갖고있기 때문에, json안에 Article 전체가 함께 날라가게 된다.
        // 우리는 article_id만 필요하고 article의 다른 세부 내용은 필요없기 때문에, dto가 articleId를 Long타입으로 갖게 설계하고 여기서도 dto의 List로 리턴해주는 것.
        List<CommentDto> dtos = comments
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());

        // 반환
        return dtos;

    }
}
