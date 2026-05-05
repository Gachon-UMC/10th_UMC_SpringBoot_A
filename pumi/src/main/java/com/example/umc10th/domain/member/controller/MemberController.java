package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.Response;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("")
    public Response<MemberResponseDTO.JoinResultDTO> join(@RequestBody MemberRequestDTO.JoinDTO request) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return Response.onSuccess(code, memberService.join(request));
    }

    @GetMapping("/me")
    public Response<MemberResponseDTO.MemberInfoDTO> getMe() {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return Response.onSuccess(code, memberService.getMe());
    }

    @PatchMapping("/me")
    public Response<MemberResponseDTO.MemberInfoDTO> updateMe(@RequestBody MemberRequestDTO.UpdateDTO request) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return Response.onSuccess(code, memberService.updateMe(request));
    }

    @DeleteMapping("/me")
    public Response<String> deleteMe() {
        BaseSuccessCode code = MemberSuccessCode.OK;
        memberService.deleteMe();
        return Response.onSuccess(code, "회원 탈퇴 성공");
    }

    @PutMapping("/notifications/settings")
    public Response<String> updateNotificationSettings(@RequestBody MemberRequestDTO.UpdateNotificationSettingsDTO request) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        memberService.updateNotificationSettings(request);
        return Response.onSuccess(code, "알림 설정 업데이트 성공");
    }

    @GetMapping("/notifications")
    public Response<MemberResponseDTO.NotificationListDTO> getNotifications() {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return Response.onSuccess(code, memberService.getNotifications());
    }
}
