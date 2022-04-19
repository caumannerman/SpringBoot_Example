package com.example.springboot_example.repository;

import com.example.springboot_example.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

//페이지처리, Sorting처리 가능한 JpaRepository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글 조회

    //특정 닉네임의 모든 댓글 조회

    
}
