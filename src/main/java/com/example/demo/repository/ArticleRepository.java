package com.example.demo.repository;

import com.example.demo.domain.Article;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Repository
public class ArticleRepository {
    @PersistenceContext
    private EntityManager em;


    //아티클 등록

    public void save(Article article){
        em.persist(article);

    }


    // 아티클 전체 조회

    //아티클 하나 조회

    public Article findOne(Long id){
        return em.find(Article.class,id);
    }


}
