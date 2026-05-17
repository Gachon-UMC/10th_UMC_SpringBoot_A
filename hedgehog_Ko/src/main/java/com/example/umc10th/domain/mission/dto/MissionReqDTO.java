package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MissionReqDTO {

    public record GetMyMissionsDTO(
            @NotNull
            @Positive
            Long userId
    ) {
    }
}
