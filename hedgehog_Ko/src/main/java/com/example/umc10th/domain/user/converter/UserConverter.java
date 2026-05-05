package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;

public class UserConverter {

    public static UserResDTO.MyInfoDTO toMyInfoDTO(User user) {
        return UserResDTO.MyInfoDTO.builder()
                .userId(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .birth(user.getBirth())
                .address(user.getAddress())
                .point(user.getPoint())
                .build();
    }
}
