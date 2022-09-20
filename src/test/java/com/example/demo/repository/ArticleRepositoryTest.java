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
        article.setArticleText("엄마는 가끔 내가 기억하지 못하는 일들을 기억하냐고 물어본다. 예를 들면 1살때 제주도에 가서 엄청 울었는데 기억나니? 그땐 왜 그렇게 울었니? 등등 정말 기억속에서 사라진듯한 어렸을적 이야기들을 여쭤보시는데 난 정말 모른다. 정확히 3살이전 기억은 머릿속에서 사라진듯이 없다. 한 4살때 부터는 간간이 기억나기도 하지만... 이것을 '유아기 기억상실증'이라고 부른다. 아직까지 과학적으로 확실히 증명된 것은 없지만 유아들의 신경이 새롭게 형성되는 과정에서 두돌이전의 과거 기억들을 잊어버리는 것으로 보면된다. \n" +
                "\n" +
                "\u200B\n" +
                "\n" +
                "이러한 현상을 작가는 굉장히 흥미롭게 표현했다. 유아기의 기억상실을 외계의 행성에서 온 '그들'이 유아기의 아이들에게 잠시 머물다 간것 이라고. '그들'은 유아기의 어린아이들에게  감정,마음,사랑,이타심을 가르쳐준다. 그리고 아이들이 만 7세가 되는 기점으로 완전히 사라져버린다. 아이들은 세상과 접촉하며 '그들'과 접촉하게 되는데, 외부와 단절된 채 보육로봇에게 키워진 아이들은 '그들'과 접촉하지 못해 이타심에 대한 대화보다는 본능 위주의 대화만 하게된다. 만 7세가 넘어도 이 외계의 행성에서 온 '그들'을 기억하는 사람이 있었는데, 그 사람이 바로 '류드밀라'이다. 류드밀라의 그림은 사람들에게 열광과 환호를 불러일으켰고, 사람들은 류드밀라의 세계를 보고 눈물을 흘렸다. \n");

        //when
        Long articleID=articleService.save(article);

        //then
        Assertions.assertEquals(article, articleRepository.findOne(articleID));
        
        
    }
}