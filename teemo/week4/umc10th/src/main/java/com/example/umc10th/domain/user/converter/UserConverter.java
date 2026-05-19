package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.store.enums.Address;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.Food;
import com.example.umc10th.domain.user.entity.Term;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.entity.mapping.UserFood;
import com.example.umc10th.domain.user.entity.mapping.UserTerm;
import com.example.umc10th.domain.user.enums.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static List<UserTerm> toUserTermList(List<Term> termList) {
        return termList.stream()
                .map(term -> UserTerm.builder().term(term).build())
                .collect(Collectors.toList());
    }

    public static List<UserFood> toUserFoodList(List<Food> foodList) {
        return foodList.stream()
                .map(food -> UserFood.builder().food(food).build())
                .collect(Collectors.toList());
    }

    public static UserResDTO.JoinResult toJoinResultDTO(User user) {
        return UserResDTO.JoinResult.builder()
                .userId(user.getId())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static User toUser(UserReqDTO.Join request) {
        Gender gender = switch (request.getGender()) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            default -> Gender.NONE;
        };

        return User.builder()
                .name(request.getName())
                .gender(gender)
                .birth(LocalDate.of(request.getBirthYear(), request.getBirthMonth(), request.getBirthDay()))
                .address(Address.valueOf(request.getAddress()))
                .detailAddress(request.getSpecAddress())
                .email(request.getEmail())
                .password(request.getPassword())
                .userFoodList(new ArrayList<>())
                .userTermList(new ArrayList<>())
                .point(0)
                .build();
    }

    public static UserResDTO.MyPage toMyPageDTO(User user) {
        return UserResDTO.MyPage.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .point(user.getPoint())
                .build();
    }
}
