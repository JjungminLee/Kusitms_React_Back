package com.example.demo.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostArticleDto {
    private Long uploaderId;
    private String articleTitle;
    private String articleText;


}
