package com.example.umc10th.domain.member.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {


    public record SignUp(
            String name,
            String gender,
            LocalDate birthDate,
            String address,
            List<String> preferredFoods
    ){}



}
