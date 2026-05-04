package com.umc10th.umc10th_kamang.domain.mission.dto;

import com.umc10th.umc10th_kamang.domain.mission.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponse {

    /**
     * 홈 화면 조회 응답 DTO
     * GET /api/home
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeDTO {
        private Long regionId;
        private String regionName;
        private Integer totalPoints;
        private Long completedMissionCount;
        private Integer missionGoalCount;
        private Integer goalRewardPoints;
        private List<MissionPreviewDTO> missions;
        private Integer currentPage;
        private Integer totalPages;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    /**
     * 홈 화면의 미션 미리보기 DTO
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreviewDTO {
        private Long missionId;
        private String storeName;
        private String storeImageUrl;
        private String condition;
        private Integer rewardPoints;
        private LocalDateTime deadline;
        private Long dDay;
    }

    /**
     * 미션 목록 조회 응답 DTO
     * GET /api/user-missions
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListDTO {
        private List<UserMissionDTO> missions;
        private Integer currentPage;
        private Integer totalPages;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    /**
     * 사용자 미션 개별 항목 DTO
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionDTO {
        private Long userMissionId;
        private String storeName;
        private String storeImageUrl;
        private String condition;
        private Integer rewardPoints;
        private MissionStatus status;
    }

    /**
     * 미션 상태 변경 결과 응답 DTO
     * PATCH /api/user-missions/{userMissionId}
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateStatusResultDTO {
        private Long userMissionId;
        private MissionStatus status;
        private LocalDateTime updatedAt;
    }
}
