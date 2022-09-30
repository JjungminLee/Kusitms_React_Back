package com.example.demo.repository;

import com.example.demo.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories

public interface ArticleRepository extends JpaRepository<Article,Long> {

    Article findByArticleTitle(String articleTitle);

    Article getReferenceById(Long id);
}
