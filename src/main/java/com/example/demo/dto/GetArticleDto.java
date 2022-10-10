package com.example.demo.dto;


import com.example.demo.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetArticleDto {
    Long articleId;
    Long uploaderId;
    String uploaderName;
    String articleTitle;
    String articleText;


    // 빌더 패턴
    public static GetArticleDto getArticle(Article article){

        return GetArticleDto.builder()
                .articleId(article.getId())
                .uploaderId(article.getMember().getId())
                .uploaderName(article.getMember().getName())
                .articleTitle(article.getArticleTitle())
                .articleText(article.getArticleText())
                .build();
    }

}
