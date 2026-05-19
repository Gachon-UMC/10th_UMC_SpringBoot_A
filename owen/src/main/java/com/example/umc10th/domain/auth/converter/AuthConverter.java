package com.example.umc10th.domain.auth.converter;

import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.enums.SocialType;

public class AuthConverter {

    public static Member toMember(AuthReqDTO.SignUp dto, String encodedPassword) {
        return Member.builder()
                .email(dto.email())
                .password(encodedPassword)
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.detailAddress())
                .socialType(SocialType.LOCAL)
                .socialId("")
                .point(0)
                .build();
    }

    public static MemberTerm toMemberTerm(Member member, Term term) {
        return MemberTerm.builder()
                .member(member)
                .term(term)
                .build();
    }

    public static MemberFood toMemberFood(Member member, Food food) {
        return MemberFood.builder()
                .member(member)
                .food(food)
                .build();
    }

    public static AuthResDTO.SignUp toSignUpResponse(Member member) {
        return AuthResDTO.SignUp.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .build();
    }
}