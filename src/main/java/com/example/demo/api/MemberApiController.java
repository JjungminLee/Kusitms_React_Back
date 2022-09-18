package com.example.demo.api;


import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booklog")
@RequiredArgsConstructor
public class MemberApiController {

    @Autowired
    private final MemberService memberService;

    @PostMapping("/member")
    public CreateMemberResponse saveMember(@RequestBody  CreateMemberRequest request){
        Member member=new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPhoneNum(request.getPhoneNum());
        member.setPassword(request.getPassword());
        Long id=memberService.join(member);
        return new CreateMemberResponse(id);

    }
    @PostMapping("/member/log-in")
    public LoginMemberResponse loginMember(@RequestBody LoginMemberRequest request){
        Long loginMemberId=memberService.loginMember(request.getEmail(),request.getPassword());
        return new LoginMemberResponse(loginMemberId);
    }

    @Data
    @AllArgsConstructor
    public static class CreateMemberResponse{

        private Long id;
    }

    @Data
    @NoArgsConstructor
    public static class CreateMemberRequest{


        private String name;
        private String email;
        private String phoneNum;
        private String password;

    }

    @Data
    @AllArgsConstructor
    public static class LoginMemberRequest{

        private String email;
        private String password;
    }

    @Data
    @AllArgsConstructor
    public static class LoginMemberResponse{

        private Long id;
    }


}
