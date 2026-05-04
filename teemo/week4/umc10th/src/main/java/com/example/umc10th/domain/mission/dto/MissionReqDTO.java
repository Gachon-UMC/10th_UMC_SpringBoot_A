package com.example.umc10th.domain.mission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class MissionReqDTO {

    @Getter
    public static class MissionCompleteDTO {
        @JsonProperty("is_completed")
        private Boolean isCompleted;
    }

    @Getter
    public static class MissionUpdateDTO {
        private String status;
    }
}
