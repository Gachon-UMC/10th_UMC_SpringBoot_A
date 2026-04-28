package com.example.umc10th.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class MissionResDTO {

    @Getter
    @Builder
    public static class HomeDTO {
        private String lacationName;
        private Integer totalPoint;
        private List<MissionInfo> missions;
    }

    @Getter
    @Builder
    public static class MissionInfo {
        private String storeName;
        private String storeCategory;
        private String content;
        private LocalDate dueDate;
        private Integer point;
        private Integer status;
    }
}
