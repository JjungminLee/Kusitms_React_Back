package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.dto.GetArticleDto;
import com.example.demo.dto.PostArticleDto;
import com.example.demo.dto.response.BaseResponse;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.ArticleRealRepository;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class ArticleApiController {

    private final ArticleRepository articleRepository;
    private final ArticleRealRepository articleRealRepository;
    private final ArticleService articleService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;



    @PostMapping("/article")
    public BaseResponse<PostArticleRes>postArticle(@RequestBody PostArticleDto postArticleDto){


        Long articleId=articleService.save(postArticleDto);
        Article article=articleRepository.getReferenceById(articleId);

        PostArticleRes postArticleRes=new PostArticleRes(article.getId());
        return new BaseResponse<>(postArticleRes);

    }
    
    
    // 모든 글 조회
    @GetMapping("/article")
    public BaseResponse<List<GetArticleDto>>getArticleList(){

        List<GetArticleDto> articleList=articleService.findAll();
        return new BaseResponse<>(articleList);


    }



    @GetMapping("/article/{articleId}")
    // 글 하나만 조회 (id값이 parameter)

    public BaseResponse<GetArticleDto>getOneArticle(@PathVariable("articleId") Long articleId){
        System.out.println(articleId);

        GetArticleDto getOneArticle=articleService.findOne(articleId);

        System.out.println(getOneArticle.getArticleTitle());
        return new BaseResponse<>(getOneArticle);

    }








    @Data
    @AllArgsConstructor
    public static class PostArticleRes{
        public Long articleId;
    }






}
