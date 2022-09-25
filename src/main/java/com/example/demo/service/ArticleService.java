package com.example.demo.service;


import com.example.demo.domain.Article;
import com.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Transactional
    public Long save(Article article){


        articleRepository.save(article);
        return article.getId();

    }
}
