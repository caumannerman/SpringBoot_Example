package com.example.springboot_example.repository;


import com.example.springboot_example.Entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
