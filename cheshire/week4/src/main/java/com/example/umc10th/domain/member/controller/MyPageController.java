package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MyPageController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ApiResponse<?> getProfile(@RequestHeader("memberId") Long memberId) {
        Member member = memberService.getMember(memberId);

        MemberResDTO.ProfileRes response = new MemberResDTO.ProfileRes(
                member.getName(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getTotalPoint()
        );
        return ApiResponse.onSuccess(MemberSuccessCode.MY_PAGE_OK, response);
    }
}
