package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.Response;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/auth/users")
    public Response<MemberResponseDTO.CreateResultDTO> createMember(@Valid @RequestBody MemberRequestDTO.CreateDTO request) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return Response.onSuccess(code, memberService.createMember(request));
    }

    @PostMapping("/users/details")
    public Response<MemberResponseDTO.MemberInfoDTO> getMember(@Valid @RequestBody MemberRequestDTO.UserIdRequestDTO request) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return Response.onSuccess(code, memberService.getMember(request.userId()));
    }

    @PostMapping("/users/points")
    public Response<MemberResponseDTO.PointInfoDTO> getMemberPoints(@Valid @RequestBody MemberRequestDTO.UserIdRequestDTO request) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return Response.onSuccess(code, memberService.getMemberPoints(request.userId()));
    }
}
