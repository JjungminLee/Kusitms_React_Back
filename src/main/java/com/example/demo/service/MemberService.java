package com.example.demo.service;


import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor //final로 선언된 애한테 생성자
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){

        validationDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    
    //중복회원 조회
    public void validationDuplicateMember(Member member){

        List<Member> findMembers=memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    
    //회원 전체 조회
    @Transactional(readOnly = true)
    public List<Member>findMembers(){
        return memberRepository.findAll();
    }



    //회원 단건 조회
    @Transactional(readOnly = true)
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }


    //회원 수정
    @Transactional
    public Long update(Long id,String name){
        Member member=memberRepository.findOne(id);
        member.setName(name); //멤버 클래스 setter로 변경
        return  id; 
    }

}
