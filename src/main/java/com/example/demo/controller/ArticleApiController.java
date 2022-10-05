package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.dto.GetArticleDto;
import com.example.demo.dto.PostArticleDto;
import com.example.demo.dto.response.BaseResponse;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class ArticleApiController {

    private final ArticleRepository articleRepository;
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

    @GetMapping("/article")
    public BaseResponse<List<GetArticleDto>>GetArticleList(){

        List<GetArticleDto> articleList=articleService.findAll();
        return new BaseResponse<>(articleList);


    }





    @Data
    @AllArgsConstructor
    public static class PostArticleRes{
        public Long articleId;
    }






}
