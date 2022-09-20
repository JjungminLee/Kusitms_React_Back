package com.example.demo.domain;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Article")
@Getter
@Setter
public class Article {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="uploader_id")
    private Member member;


    private String articleTitle;

    @Lob
    @Column(length=200000)
    private String articleText;

    @OneToMany(mappedBy = "article")
    private List<HashtagPost> hashtagPost =new ArrayList<>();
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime created;


    @Column(name="updated",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updated;
    @ColumnDefault("'A'")
    private String status;



}
