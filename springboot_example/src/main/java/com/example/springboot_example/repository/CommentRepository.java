package com.example.springboot_example.repository;

import com.example.springboot_example.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//페이지처리, Sorting처리 가능한 JpaRepository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 게시글의 id로 해당 글에 달린 모든 댓글 조회
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    //특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);


}
