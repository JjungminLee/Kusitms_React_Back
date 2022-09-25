package com.example.demo.api;


import com.example.demo.domain.UserInfo;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.dto.response.BaseResponse;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
        String jwt=jwtTokenProvider.createToken(user.getName(),user.getRoles());
        CreateUserRes createUserRes=new CreateUserRes(id,jwt);
        return new BaseResponse<>(createUserRes);


    }
    @PostMapping("/login")
    public BaseResponse<LoginUserRes> logIn(@RequestBody LoginDto loginDto){
        UserInfo user=userRepository.findByEmail(loginDto.getEmail());
        String jwt=jwtTokenProvider.createToken(user.getName(),user.getRoles());
        LoginUserRes loginUserRes=new LoginUserRes(jwt);
        return new BaseResponse<>(loginUserRes);
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

        public String jwt;
    }

    //로그아웃
    @GetMapping(value = "/logout")
    public BaseResponse<HttpServletResponse> logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return new BaseResponse<>(response);
    }


}
