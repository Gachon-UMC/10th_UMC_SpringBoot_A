package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users") //UserController 니까 Mapping을 users까지.. 했다..
public class UserController {

    private final UserService UserService;

    @GetMapping("/me")
    public ApiResponse<UserResDTO.MyPage> getMyPage() {
        UserResDTO.MyPage response = new UserResDTO.MyPage(
                "nickname012",
                "https://example.com/profile.png",
                "user@example.com",
                "01012345678",
                2500
        );
        BaseSuccessCode code = UserSuccessCode.OK; // 아직 UserSuccessCode를 안 만듦.. 시간 부족.. 시간 나면 바로 만들게요..
        return ApiResponse.onSuccess(code, UserService.getMyPage());
    }

    @DeleteMapping("/me")
    public ApiResponse<String> deleteMe() {
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, UserService.deleteMe());
    }

    @GetMapping("/me/profile")
    public ApiResponse<UserResDTO.Profile> getProfile() {
        UserResDTO.Profile response = new UserResDTO.Profile(
                "nickname012",
                "https://example.com/profile.png",
                "user@example.com",
                "01012345678"
        );
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, UserService.getProfile());
    }

    @PatchMapping("/me/profile")
    public ApiResponse<UserResDTO.Profile> updateProfile(
            @RequestBody UserReqDTO.UpdateProfile request
    ) {
        UserResDTO.Profile response = new UserResDTO.Profile(
                request.nickname(),
                request.profileUrl(),
                "user@example.com",
                "01012345678"
        );
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, UserService.updateProfile(request));
    }

    @PostMapping("/me/phone-verifications/request")
    public ApiResponse<UserResDTO.PhoneVerificationResult> requestPhoneVerification(
            @RequestBody UserReqDTO.PhoneVerificationRequest request
    ) {
        UserResDTO.PhoneVerificationResult response = new UserResDTO.PhoneVerificationResult(
                request.phone(),
                "REQUESTED"
        );
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, UserService.requestPhoneVerification(request));
    }

    @PostMapping("/me/phone-verifications/confirm")
    public ApiResponse<UserResDTO.PhoneVerificationResult> confirmPhoneVerification(
            @RequestBody UserReqDTO.PhoneVerificationConfirm request
    ) {
        UserResDTO.PhoneVerificationResult response = new UserResDTO.PhoneVerificationResult(
                request.phone(),
                "VERIFIED"
        );
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, UserService.confirmPhoneVerification(request));
    }

    @GetMapping("/me/points")
    public ApiResponse<UserResDTO.Point> getMyPoint() {
        UserResDTO.Point response = new UserResDTO.Point(2500);
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, UserService.getMyPoint());
    }

    @GetMapping("/me/reviews")
    public ApiResponse<UserResDTO.ReviewList> getMyReviews(
            @RequestParam(defaultValue = "0") Integer page
    ) {
        UserResDTO.ReviewPreview review = new UserResDTO.ReviewPreview(
                1L,
                123L,
                "수내 맛집",
                4.5,
                "사장님이 맛있고 음식이 잘생겼어요."
        );

        UserResDTO.ReviewList response = new UserResDTO.ReviewList(
                page,
                10,
                false,
                List.of(review)
        );

        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, UserService.getMyReviews(page));
    }

    @GetMapping("/me/inquiries")
    public ApiResponse<UserResDTO.InquiryList> getMyInquiries() {
        UserResDTO.InquiryPreview inquiry = new UserResDTO.InquiryPreview(
                1L,
                "이 기능을 변경해주세요.",
                "PENDING"
        );

        UserResDTO.InquiryList response = new UserResDTO.InquiryList(List.of(inquiry));

        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, UserService.getMyInquiries());
    }

    @PostMapping("/me/inquiries")
    public ApiResponse<UserResDTO.InquiryCreated> createInquiry(
            @RequestBody UserReqDTO.CreateInquiry request
    ) {
        UserResDTO.InquiryCreated response = new UserResDTO.InquiryCreated(1L);

        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, UserService.createInquiry(request));
    }

    @GetMapping("/me/notification-settings")
    public ApiResponse<UserResDTO.NotificationSettings> getNotificationSettings() {
        UserResDTO.NotificationSettings response = new UserResDTO.NotificationSettings(true);

        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, UserService.getNotificationSettings());
    }

    @PatchMapping("/me/notification-settings")
    public ApiResponse<UserResDTO.NotificationSettings> updateNotificationSettings(
            @RequestBody UserReqDTO.UpdateNotificationSettings request
    ) {
        UserResDTO.NotificationSettings response = new UserResDTO.NotificationSettings(
                request.reviewReplyNotificationEnabled()
        );

        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, UserService.updateNotificationSettings(request));
    }
}