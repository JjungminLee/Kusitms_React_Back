package com.example.demo.domain;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Hashtag")
public class Hashtag {

    public Hashtag(){

    }
    @Id
    @GeneratedValue
    @Column(name="hashtag_id")
    private Long id;
    private String hashtag;
    @Column(name="created",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp created;
    @Column(name="updated",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updated;
    @ColumnDefault("'A'")
    private String status;

    @OneToOne(fetch=FetchType.LAZY)
    private HashtagPost hashtagPost;


}
