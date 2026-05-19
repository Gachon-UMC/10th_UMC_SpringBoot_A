package com.example.umc10th.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class UserResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResult {
        private Long userId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPage {
        private String name;
        private String email;
        private String phoneNumber;
        private Integer point;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TermList {
        private List<TermDTO> terms;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TermDTO {
        private Long termId;
        private String title;
        private String body;
        private Boolean isOptional;
    }
}
