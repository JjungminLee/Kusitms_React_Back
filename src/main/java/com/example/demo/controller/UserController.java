package com.example.demo.controller;


import com.example.demo.domain.UserInfo;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.dto.response.BaseException;
import com.example.demo.dto.response.BaseResponse;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController

public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    
    
    //회원가입

    private final UserService userService;
    @PostMapping("/user")
    public BaseResponse<CreateUserRes> signUp(@RequestBody UserInfoDto userInfoDto){
        userService.save(userInfoDto);
        UserInfo user=userRepository.findByEmail(userInfoDto.getEmail());
        Long id=user.getId();
        String jwt=jwtTokenProvider.createToken(user.getId(),user.getRoles());
        System.out.println(jwt);
        CreateUserRes createUserRes=new CreateUserRes(id,jwt);
        return new BaseResponse<>(createUserRes);


    }
    
    //react-query get요청위함
    @GetMapping("/user")
    public BaseResponse<SwrUserRes>userInfo(HttpServletRequest httpServletRequest)throws BaseException {

        System.out.println(httpServletRequest.getMethod());
        System.out.println(httpServletRequest.getHeader("X-ACCESS-TOKEN"));


        String userJwt= jwtTokenProvider.getUserPk(jwtTokenProvider.getJwt()); //id값이 string으롷
        System.out.println(userJwt);
        Long id= Long.parseLong(userJwt);//string인 id를 long으로 파싱
        System.out.println(id);

        UserInfo user=userRepository.getReferenceById(id);

        String jwt=jwtTokenProvider.createToken(user.getId(),user.getRoles());
        SwrUserRes swrUserRes=new SwrUserRes(id, user.getName(), user.getEmail(),user.getPhoneNum(),user.getPassword(),jwt);
        return new BaseResponse<>(swrUserRes);



    }

    @PostMapping("/login")
    public BaseResponse<LoginUserRes> logIn(@RequestBody LoginDto loginDto){

        //email에 해당하는 유저가 있는지 확인
        UserInfo user=userRepository.findByEmail(loginDto.getEmail());
        Long id=user.getId();
        String name=user.getName();
        String jwt=jwtTokenProvider.createToken(user.getId(),user.getRoles());
        LoginUserRes loginUserRes=new LoginUserRes(id,name,jwt);
        return new BaseResponse<>(loginUserRes);
    }

    //로그아웃
    @GetMapping(value = "/logout")
    public BaseResponse<HttpServletResponse> logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return new BaseResponse<>(response);
    }

    @Data
    @AllArgsConstructor
    public static class CreateUserRes{

        public Long id;
        public String jwt;
    }

    @Data
    @AllArgsConstructor
    public static class LoginUserRes{

        public Long id;

        public String name;

        public String jwt;
    }

    @Data
    @AllArgsConstructor
    public static class SwrUserRes{

        public Long id;
        public String name;
        public String email;
        public String phoneNUm;
        public String password;
        public String jwt;
    }






}
