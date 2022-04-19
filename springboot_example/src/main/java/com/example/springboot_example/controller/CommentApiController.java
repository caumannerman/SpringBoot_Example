package com.example.springboot_example.controller;

import com.example.springboot_example.Entity.Comment;
import com.example.springboot_example.dto.CommentDto;
import com.example.springboot_example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    //특정 게시글에 대한 댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable Long articleId){
        //서비스에게 위임
        List<CommentDto> dtos = commentService.getAllComments(articleId);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);

    }


    //댓글 생성


    //댓글 수정


    //댓글 삭제




}