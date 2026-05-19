package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.enums.Agreement;

import java.util.List;

public class MemberConverter {

    public static Member toMember(MemberReqDTO.SignUp request, String encodedPassword) {
        return Member.builder()
                .email(request.email())
                .password(encodedPassword)
                .name(request.name())
                .gender(request.gender())
                .birth(request.birthDate())
                .detailAddress(request.detailAddress())
                .totalPoint(0)
                .build();
    }

    public static List<MemberFood> toMemberFoodList(Member member, List<Food> foods) {
        return foods.stream()
                .map(food -> MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build())
                .toList();
    }

    public static List<MemberTerm> toMemberTermList(Member member, List<Term> terms) {
        return terms.stream()
                .map(term -> MemberTerm.builder()
                        .member(member)
                        .term(term)
                        .agreement(Agreement.AGREED)
                        .build())
                .toList();
    }

    public static MemberResDTO.ProfileRes toProfileRes(Member member) {
        return new MemberResDTO.ProfileRes(
                member.getName(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getTotalPoint()
        );
    }
}
