package com.example.umc10th.domain.member.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    @Getter
    public static class SignUp{
        private String name;
        private String gender;
        private LocalDate birthDate;
        private String address;
        private List<String> preferredFoods;
    }

}
