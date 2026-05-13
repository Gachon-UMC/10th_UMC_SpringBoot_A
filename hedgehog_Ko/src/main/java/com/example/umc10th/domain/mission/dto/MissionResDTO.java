package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {
    // 내 미션 목록 조회
    @Builder
    public record MyMissionListDTO(
            List<MyMissionPreviewDTO> missions,
            Long nextCursor,
            Boolean hasNext,
            Integer size
    ) {
    }

    @Builder
    public record MyMissionPreviewDTO(
            Long userMissionId,
            Long missionId,
            Long storeId,
            String storeName,
            Long foodCategoryId,
            String foodCategoryName,
            String missionCondition,
            Integer point,
            LocalDate deadline, // 실제 마감일
            Integer dDay, // WF에 뜨는 D-7 부분
            MissionStatus status
    ) {
    }

    @Builder
    public record StartMissionResultDTO(
            Long userMissionId,
            MissionStatus status,
            LocalDateTime startedAt
    ) {
    }

    @Builder
    public record CancelMissionResultDTO(
            Long userMissionId,
            MissionStatus status,
            LocalDateTime canceledAt
    ) {
    }

    @Builder
    public record VerificationRequestResultDTO(
            Long userMissionId,
            String verificationCode,
            LocalDateTime expiresAt
    ) {
    }

    @Builder
    public record VerificationConfirmResultDTO(
            Long userMissionId,
            MissionStatus status,
            Long earnedPoint,
            Integer totalPoint,
            LocalDateTime completedAt
    ) {
    }
}
