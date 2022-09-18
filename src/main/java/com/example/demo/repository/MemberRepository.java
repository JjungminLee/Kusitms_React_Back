package com.example.demo.repository;


import com.example.demo.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // 멤버 저장
    public void save(Member member){
        em.persist(member);
    }

    //멤버 조회 (아이디로 멤버 한명 조회)
    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    //멤버 조회 (로그인 용 -> 이메일,password로)
    public List<Member> loginMember(String email,String password){
        return em.createQuery("select m from Member m where m.password=:password and m.email=:email",Member.class)
                .setParameter( "password",password)
                .setParameter("email",email)
                .getResultList();


    }

    //전체 멤버 조회
    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();
    }
    
    //이름으로 조회
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name=:name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }
    

}
