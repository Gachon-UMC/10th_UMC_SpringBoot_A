package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService; // 추가

    // 홈 화면 정보 조회: GET /api/home
    @GetMapping("/home")
    public ApiResponse<MemberResDTO.Home> getHome(
            @RequestHeader("Authorization") String authorization
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.HOME_OK, null);
    }

    // [화면3] 마이페이지 정보 조회: GET /api/members/{memberId}
    @GetMapping("/members/{memberId}")
    public ApiResponse<MemberResDTO.MyPage> getMyPage(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long memberId // 추가
    ) {
        MemberResDTO.MyPage result = memberService.getMyPage(memberId); // 서비스 연결
        return ApiResponse.onSuccess(MemberSuccessCode.MYPAGE_OK, result);
    }

    // 내 정보 수정: PATCH /api/members
    @PatchMapping("/members")
    public ApiResponse<MemberResDTO.UpdateInfo> updateInfo(
            @RequestHeader("Authorization") String authorization,
            @RequestBody MemberReqDTO.UpdateInfo dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.UPDATE_OK, null);
    }

    // 계정 탈퇴: DELETE /api/members
    @DeleteMapping("/members")
    public ApiResponse<MemberResDTO.Withdraw> withdraw(
            @RequestHeader("Authorization") String authorization
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.WITHDRAW_OK, null);
    }

    // 내 포인트 조회: GET /api/members/points
    @GetMapping("/members/points")
    public ApiResponse<MemberResDTO.Point> getPoint(
            @RequestHeader("Authorization") String authorization
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.POINT_OK, null);
    }
}