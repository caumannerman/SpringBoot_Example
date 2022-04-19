package com.example.springboot_example.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity //새로운 댓글 테이블이 만들어진다!!
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
}
