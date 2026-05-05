package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Notification;
import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.MemberRole;
import com.example.umc10th.domain.member.enums.MemberStatus;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static User toUser(MemberRequestDTO.CreateDTO request) {
        Gender gender = null;
        if (request.gender() != null) {
            gender = Gender.valueOf(request.gender().toUpperCase());
        }

        return User.builder()
            .name(request.name())
            .gender(gender)
            .birthday(request.birthday())
            .address(request.address())
            .role(MemberRole.USER)
            .status(MemberStatus.ACTIVE)
            .point(0)
            .email("temp@example.com")
            .build();
    }

    public static MemberResponseDTO.CreateResultDTO toCreateResultDTO(User user) {
        return new MemberResponseDTO.CreateResultDTO(
            user.getId(),
            user.getCreatedAt()
        );
    }

    public static MemberResponseDTO.MemberInfoDTO toMemberInfoDTO(User user) {
        return new MemberResponseDTO.MemberInfoDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getNickname(),
            user.getGender() != null ? user.getGender().name() : null, // 누락방지
            user.getAddress()
        );
    }

    public static MemberResponseDTO.PointInfoDTO toPointInfoDTO(User user) {
        return new MemberResponseDTO.PointInfoDTO(
            user.getId(),
            user.getPoint()
        );
    }
}
