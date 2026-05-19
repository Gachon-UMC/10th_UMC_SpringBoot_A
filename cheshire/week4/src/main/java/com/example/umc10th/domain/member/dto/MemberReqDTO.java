package com.example.umc10th.domain.member.dto;


import com.example.umc10th.domain.member.enums.Gender;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class MemberReqDTO {


    public record SignUp(
            String name,
            String email,
            String password,
            Gender gender,
            LocalDate birthDate,
            String detailAddress,
            List<Long> preferredFoods,
            List<Long> terms
    ){}



}
