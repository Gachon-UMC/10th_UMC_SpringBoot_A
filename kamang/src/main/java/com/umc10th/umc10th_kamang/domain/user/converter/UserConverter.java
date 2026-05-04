package com.umc10th.umc10th_kamang.domain.user.converter;

import com.umc10th.umc10th_kamang.domain.user.dto.UserResponse;
import com.umc10th.umc10th_kamang.domain.user.entity.User;

public class UserConverter {

    private UserConverter() {
    }

    public static UserResponse.MyPageDTO toMyPageDTO(User user) {
        return UserResponse.MyPageDTO.builder()
                .userId(user.getId())
                .nickname(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .isPhoneVerified(user.getIsPhoneVerified())
                .totalPoints(user.getTotalPoints())
                .build();
    }
}
