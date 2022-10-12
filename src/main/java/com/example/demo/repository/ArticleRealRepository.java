package com.example.demo.repository;

import com.example.demo.domain.Article;
import com.example.demo.dto.GetArticleDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRealRepository {

    private final EntityManager em;

    public List<Article> findAllWithArticleMember(){

        return  em.createQuery(
                "select a from Article a  "+
                        "join fetch a.member m ",Article.class)
                .getResultList();

    }




}
