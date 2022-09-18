package com.example.demo.domain;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import javax.validation.constraints.NotEmpty;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Member")
@Getter
@Setter
public class Member {

    //jpa에서 동적으로 객체 생성할 떄 Reflection API를 이용하는데 기본생성자가 없으면 못 만들어..
    public Member(){

    }
    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long Id;
    @NotEmpty(message="user name cannot be empty")
    private String name;
    @NotEmpty(message="user email cannot be empty")
    private String email;
    @NotEmpty(message="phone number cannot be empty")
    private String phoneNum;
    @NotEmpty(message="password cannot be empty")
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Article> articles=new ArrayList<>();

    @Column(name="created",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp created;
    @Column(name="updated",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updated;
    @ColumnDefault("'A'")
    private String status;
}
