package com.example.umc10th.domain.auth.dto;

import java.time.LocalDate;
import java.util.List;

public class AuthReqDTO {

    // POST /auth/signup
    public record SignUp(
            String email,
            String password,
            String name,
            String phoneNumber,
            String gender,
            LocalDate birth,
            String address,
            String detailAddress
    ) {}

    // POST /auth/login
    public record Login(
            String email,
            String password
    ) {}

    // POST /auth/preferences
    public record Preference(
            List<Long> categoryIds
    ) {}

    // POST /auth/terms
    public record Terms(
            List<Long> agreedTermIds
    ) {}
}