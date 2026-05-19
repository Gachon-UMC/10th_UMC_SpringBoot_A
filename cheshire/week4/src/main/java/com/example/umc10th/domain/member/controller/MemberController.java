package com.example.umc10th.domain.member.controller;


import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.sun.net.httpserver.Authenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MemberController {
    private final MemberService memberService;



    @PostMapping("/signup")
    public ApiResponse<?> signUp(@RequestBody MemberReqDTO.SignUp request){
        BaseSuccessCode code = MemberSuccessCode.SIGN_UP_CREATED;
        return ApiResponse.onSuccess(code, memberService.signUp(request));
    }

}
