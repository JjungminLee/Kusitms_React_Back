package com.example.demo.api;

import com.example.demo.domain.Article;
import com.example.demo.domain.UserInfo;
import com.example.demo.dto.PostArticleDto;
import com.example.demo.dto.response.BaseResponse;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ArticleService;
import javafx.geometry.Pos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/article2")
    public BaseResponse<PostArticleRes>postArticle2(@RequestBody PostArticleReq postArticleReq){
        System.out.println(46);
        PostArticleRes postArticleRes=new PostArticleRes(postArticleReq.getUploaderId());
        return  new BaseResponse<>(postArticleRes);
    }


    @Data
    @AllArgsConstructor
    public static class PostArticleReq{
        public Long uploaderId;



    }
    @Data
    @AllArgsConstructor
    public static class PostArticleRes{
        public Long articleId;
    }



    @Data
    @AllArgsConstructor
    public static class GetArticleRes{
        Long uploaderId;
        String uploaderName;
        String articleTitle;
        String articleText;

    }


}
