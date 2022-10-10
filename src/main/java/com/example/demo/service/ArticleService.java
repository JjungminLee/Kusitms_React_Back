package com.example.demo.service;


import com.example.demo.domain.Article;
import com.example.demo.domain.UserInfo;
import com.example.demo.dto.GetArticleDto;
import com.example.demo.dto.PostArticleDto;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class ArticleService {


    @Autowired
    private final ArticleRepository articleRepository;

    @Autowired
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }


    public Long save(PostArticleDto postArticleDto){


        UserInfo user=userRepository.getReferenceById(postArticleDto.getUploaderId());
        Article article=new Article(user,postArticleDto.getArticleTitle(),postArticleDto.getArticleText());


        Long id=articleRepository.saveAndFlush(article).getId();
        return id;





    }

    public List<GetArticleDto> findAll(){



        return articleRepository.findAll().stream()
                .map(GetArticleDto::getArticle).collect(Collectors.toList());
    }

    public GetArticleDto findOne(Long id){

        Article article=articleRepository.getReferenceById(id);

        return new GetArticleDto(article.getId(),article.getMember().getId(),article.getMember().getName(),
                article.getArticleTitle(),article.getArticleText());

    }

}
