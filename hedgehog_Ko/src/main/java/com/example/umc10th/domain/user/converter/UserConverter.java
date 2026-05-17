package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;

public class UserConverter {

    public static User toUser(UserReqDTO.SignupDTO request, String encodedPassword) {
        return User.builder()
                .name(request.getName())
                .nickname(request.getNickname())
                .gender(request.getGender())
                .birth(request.getBirth())
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .socialUid(request.getSocialUid())
                .socialType(request.getSocialType())
                .email(request.getEmail())
                .password(encodedPassword)
                .phoneNumber(request.getPhoneNumber())
                .profileImageUrl(request.getProfileImageUrl())
                .build();
    }

    public static UserResDTO.SignupResultDTO toSignupResultDTO(User user) {
        return UserResDTO.SignupResultDTO.builder()
                .userId(user.getId())
                .createdAt(user.getCreatedAt())
                .build();
    }

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

    public static UserResDTO.UpdateMyInfoResultDTO toUpdateMyInfoResultDTO(User user) {
        return UserResDTO.UpdateMyInfoResultDTO.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .address(user.getAddress())
                .detailAddress(user.getDetailAddress())
                .profileImageUrl(user.getProfileImageUrl())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static UserResDTO.DeleteUserResultDTO toDeleteUserResultDTO(User user) {
        return UserResDTO.DeleteUserResultDTO.builder()
                .userId(user.getId())
                .deletedAt(user.getDeletedAt())
                .build();
    }

    public static UserResDTO.PointDTO toPointDTO(User user) {
        return UserResDTO.PointDTO.builder()
                .point(user.getPoint())
                .build();
    }
}
