package com.example.demo.repository;

import com.example.demo.domain.Article;
import com.example.demo.service.ArticleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleService articleService;

    @Autowired
    EntityManager em;

    @Test
    void save() {
        
        //준비 given

        Article article=new Article();
        article.setArticleTitle("공상과학도 낭만적일 수 있다");
        article.setArticleText("엄마는 가끔 내가 기억하지 못하는 일들을 기억하냐고 물어본다. 예를 들면 1살때 제주도에 가서 엄청 울었는데 기억나니? 그땐 왜 그렇게 울었니? 등등 정말 기억속에서 사라진듯한 어렸을적 이야기들을 여쭤보시는데 난 정말 모른다. 정확히 3살이전 기억은 머릿속에서 사라진듯이 없다. 한 4살때 부터는 간간이 기억나기도 하지만... 이것을 '유아기 기억상실증'이라고 부른다. 아직까지 과학적으로 확실히 증명된 것은 없지만 유아들의 신경이 새롭게 형성되는 과정에서 두돌이전의 과거 기억들을 잊어버리는 것으로 보면된다. ");

        //when
        Long articleID=articleService.save(article);

        //then
        Assertions.assertEquals(article, articleRepository.findOne(articleID));
        
        
    }
}