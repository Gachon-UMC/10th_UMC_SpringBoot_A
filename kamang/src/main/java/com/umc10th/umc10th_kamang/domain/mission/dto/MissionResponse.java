package com.umc10th.umc10th_kamang.domain.mission.dto;

import com.umc10th.umc10th_kamang.domain.mission.enums.MissionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "홈 화면 조회 응답")
    public static class HomeDTO {
        @Schema(description = "현재 선택된 지역 ID", example = "1")
        private Long regionId;
        @Schema(description = "현재 선택된 지역명", example = "안암동")
        private String regionName;
        @Schema(description = "사용자 보유 포인트", example = "999999")
        private Integer totalPoints;
        @Schema(description = "완료한 미션 수", example = "7")
        private Long completedMissionCount;
        @Schema(description = "목표 미션 수", example = "10")
        private Integer missionGoalCount;
        @Schema(description = "목표 달성 보상 포인트", example = "1000")
        private Integer goalRewardPoints;
        @Schema(description = "도전 가능한 미션 목록")
        private List<MissionPreviewDTO> missions;
        @Schema(description = "현재 페이지 번호", example = "0")
        private Integer currentPage;
        @Schema(description = "전체 페이지 수", example = "3")
        private Integer totalPages;
        @Schema(description = "전체 미션 수", example = "25")
        private Long totalElements;
        @Schema(description = "첫 페이지 여부", example = "true")
        private Boolean isFirst;
        @Schema(description = "마지막 페이지 여부", example = "false")
        private Boolean isLast;
    }

    /**
     * 홈 화면의 미션 미리보기 DTO
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "홈 화면 미션 미리보기")
    public static class MissionPreviewDTO {
        @Schema(description = "미션 ID", example = "1")
        private Long missionId;
        @Schema(description = "가게명", example = "반이학생마라탕")
        private String storeName;
        @Schema(description = "가게 대표 이미지 URL", example = "https://example.com/store.png")
        private String storeImageUrl;
        @Schema(description = "미션 달성 조건", example = "10,000원 이상의 식사시")
        private String condition;
        @Schema(description = "미션 완료 보상 포인트", example = "500")
        private Integer rewardPoints;
        @Schema(description = "미션 마감 기한", example = "2026-05-31T23:59:59")
        private LocalDateTime deadline;
        @Schema(description = "마감까지 남은 일수", example = "7")
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
    @Schema(description = "내 미션 목록 조회 응답")
    public static class MissionListDTO {
        @Schema(description = "사용자 미션 목록")
        private List<UserMissionDTO> missions;
        @Schema(description = "현재 페이지 번호", example = "0")
        private Integer currentPage;
        @Schema(description = "전체 페이지 수", example = "3")
        private Integer totalPages;
        @Schema(description = "전체 미션 수", example = "25")
        private Long totalElements;
        @Schema(description = "첫 페이지 여부", example = "true")
        private Boolean isFirst;
        @Schema(description = "마지막 페이지 여부", example = "false")
        private Boolean isLast;
    }

    /**
     * 사용자 미션 개별 항목 DTO
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "사용자 미션 항목")
    public static class UserMissionDTO {
        @Schema(description = "사용자 미션 ID", example = "1")
        private Long userMissionId;
        @Schema(description = "가게명", example = "반이학생마라탕")
        private String storeName;
        @Schema(description = "가게 대표 이미지 URL", example = "https://example.com/store.png")
        private String storeImageUrl;
        @Schema(description = "미션 달성 조건", example = "12,000원 이상의 식사를 하세요!")
        private String condition;
        @Schema(description = "미션 보상 포인트", example = "500")
        private Integer rewardPoints;
        @Schema(description = "사용자 미션 상태", example = "COMPLETED")
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
