package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.global.validation.annotation.CheckPage;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MissionReqDTO {

    @Getter
    public static class MissionComplete {
        @JsonProperty("is_completed")
        private Boolean isCompleted;
    }

    @Getter
    public static class MissionUpdate {
        private String status;
    }

    @Getter
    public static class OngoingMissionList {
        @NotNull
        private Long userId;
        @CheckPage
        private Integer page;
    }
}
