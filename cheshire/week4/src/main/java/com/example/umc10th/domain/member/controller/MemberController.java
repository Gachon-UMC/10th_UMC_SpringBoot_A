package com.example.umc10th.domain.member.controller;


import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {
    private final MemberService memberService;

    // 아무것도 받지 않은 경우
    @GetMapping("/test")
    public String test(){
        return "test";
    }


    @PostMapping("/signup")
    public ApiResponse<?> signUp(@RequestBody MemberReqDTO.SignUp request){
        return null;
    }

}
