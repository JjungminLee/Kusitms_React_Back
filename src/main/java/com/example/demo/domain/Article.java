package com.example.demo.domain;


import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Article")
public class Article {

    public  Article(){

    }

    @Id
    @GeneratedValue
    @Column(name="article_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="uploader_id")
    private Member member;


    private String articleTitle;
    private String articleText;

    @OneToMany(mappedBy = "article")
    private List<HashtagPost> hashtagPost =new ArrayList<>();


    @Column(name="created",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp created;
    @Column(name="updated",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updated;
    @ColumnDefault("'A'")
    private String status;



}
