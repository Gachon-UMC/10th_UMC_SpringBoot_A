package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.Response;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/auth/users")
    public Response<MemberResponseDTO.CreateResultDTO> createMember(@RequestBody MemberRequestDTO.CreateDTO request) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return Response.onSuccess(code, memberService.createMember(request));
    }

    @GetMapping("/users/details")
    public Response<MemberResponseDTO.MemberInfoDTO> getMember(@RequestParam Long userId) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return Response.onSuccess(code, memberService.getMember(userId));
    }

    @GetMapping("/users/points")
    public Response<MemberResponseDTO.PointInfoDTO> getMemberPoints(@RequestParam Long userId) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return Response.onSuccess(code, memberService.getMemberPoints(userId));
    }
}
