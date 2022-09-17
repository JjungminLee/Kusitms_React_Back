package com.example.demo.domain;


import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="HashtagPost")
public class HashtagPost {
    @Id
    @GeneratedValue
    private Long Id;

    //하나의 article은 여러개 해시태그post 할 수 있음
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="article_id")
    private Article article;

    //하나의 해시태그는 하나의 해시태그 post 가능
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="hashtag_id")
    private Hashtag hashtag;


    @Column(name="created",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp created;
    @Column(name="updated",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updated;
    @ColumnDefault("'A'")
    private String status;


}
