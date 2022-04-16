package com.example.springboot_example.repository;


import com.example.springboot_example.Entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {

  @Override
  List<Article> findAll();
}
