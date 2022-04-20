package com.example.springboot_example.service;


import com.example.springboot_example.Entity.Article;
import com.example.springboot_example.Entity.Comment;
import com.example.springboot_example.dto.CommentDto;
import com.example.springboot_example.repository.ArticleRepository;
import com.example.springboot_example.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional // DB를 건드리고있기 때문에!!  오류에 대비~
    public CommentDto create(Long articleId, CommentDto dto) {
        //게시글 조회 및 예외 발생
        // 받아온 articleID가 유효하지 않은 경우
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));

        //댓글 Entity 생성
        //static 메서드 사용해서 Comment 객체 생성 - 받아온 dto의 내용을 해당 article에 달린 COmment가 되도록 만들어준다
        Comment comment = Comment.createComment(dto,article);

        //댓글 Entity를 DB로 저장
        Comment created = commentRepository.save(comment);

        //DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));

        //댓글 수정
        target.patch(dto);

        //DB로 갱신
        Comment updated = commentRepository.save(target);

        //댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회(및 예외 발생)
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));

        // 댓글 삭제
        commentRepository.delete(target);

        //삭제 댓글 DTO로 반환
        return CommentDto.createCommentDto(target);
    }
}
