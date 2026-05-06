package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;

public class UserConverter {

    public static UserResDTO.MyPageDTO toMyPageDTO(User user) {
        return UserResDTO.MyPageDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .point(user.getPoint())
                .build();
    }
}
