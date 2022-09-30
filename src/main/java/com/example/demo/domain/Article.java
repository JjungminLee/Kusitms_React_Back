package com.example.demo.domain;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name="Article")
@Getter
@Setter
public class Article {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_id")
    private Long id;

    @ManyToOne(targetEntity = UserInfo.class ,fetch= LAZY)
    @JoinColumn(name="uploader_id")
    private UserInfo member;



    private String articleTitle;


    @Lob
    @Basic(fetch=LAZY)
    private String articleText;


    @Column(name="created",columnDefinition = "TIMESTAMP" )
    private LocalDateTime created;


    @Column(name="updated",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updated;
    @ColumnDefault("'A'")
    private String status;

    @Builder
    public Article(UserInfo member, String articleTitle, String articleText) {
        this.member=member;
        this.articleTitle=articleTitle;
        this.articleText=articleText;

    }
}
