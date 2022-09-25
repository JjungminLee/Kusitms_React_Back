package com.example.demo.repository;

import com.example.demo.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo,Long> {
    //이메일 찾기
    UserInfo findByEmail(String email);

    //이메일이 존재하는지
    boolean existsByEmail(String email);


}
