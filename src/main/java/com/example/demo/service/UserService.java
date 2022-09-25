package com.example.demo.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.domain.UserInfo;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Spring Security 필수 메소드 구현
     *
     * @param email 이메일
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     */

    //이메일을 통해 load
    @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        try{
            return userRepository.findByEmail(email);
        }
        catch(Exception e){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }


    }

    /**
     * 회원정보 저장
     *
     * @param infoDto 회원정보가 들어있는 DTO
     * @return 저장되는 회원의 PK
     */
    //id반환
    public void save(UserInfoDto infoDto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        UserInfo userInfo=new UserInfo(infoDto.getName(),infoDto.getEmail(),infoDto.getPhoneNum(),infoDto.getPassword());

        userRepository.save(userInfo);
    }
}