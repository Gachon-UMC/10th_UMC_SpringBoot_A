package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MissionRequestDTO {

    public record UserMissionSearchRequestDTO(
        @NotNull
        @Min(1)
        Long userId,
        Boolean isCompleted,
        @Min(1)
        Long regionId,
        @Min(1)
        Long cursor
    ) {}

    public record OngoingMissionRequestDTO(
        @NotNull
        @Min(1)
        Long userId,
        @NotNull
        @Min(0)
        Integer page,
        @NotNull
        @Min(1)
        Integer size
    ) {}

    public record UserIdRequestDTO(
        @NotNull
        @Min(1)
        Long userId
    ) {}
}
