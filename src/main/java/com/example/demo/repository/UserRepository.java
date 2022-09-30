package com.example.demo.repository;

import com.example.demo.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo,Long> {


    //이메일로 UserInfo 반환
    UserInfo findByEmail(String email);
    
    // id로 UserInfo 반환

    UserInfo getReferenceById(Long id);



}
