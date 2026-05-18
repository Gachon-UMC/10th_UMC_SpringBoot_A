package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MissionRequestDTO {

    public record UserMissionSearchRequestDTO(
        Boolean isCompleted,
        @Min(1)
        Long regionId,
        @Min(1)
        Long cursor
    ) {}

    public record OngoingMissionRequestDTO(
        @NotNull
        @Min(0)
        Integer page,
        @NotNull
        @Min(1)
        Integer size
    ) {}
}
