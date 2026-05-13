package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.foodCategory.entity.FoodCategory;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.entity.mapping.UserMission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MyMissionPreviewDTO toMyMissionPreviewDTO(UserMission userMission) {
        Mission mission = userMission.getMission();
        Store store = mission.getStore();
        FoodCategory foodCategory = store.getFoodCategory();

        return MissionResDTO.MyMissionPreviewDTO.builder()
                .userMissionId(userMission.getId())
                .missionId(mission.getId())
                .storeId(store.getId())
                .storeName(store.getName())
                .foodCategoryId(foodCategory.getId())
                .foodCategoryName(foodCategory.getName())
                .missionCondition(mission.getCondition())
                .point(mission.getPoint())
                .deadline(userMission.getDeadline())
                .dDay(calculateDDay(userMission.getDeadline()))
                .status(userMission.getStatus())
                .build();
    }

    public static MissionResDTO.MyMissionListDTO toMyMissionListDTO(
            List<UserMission> userMissions,
            Long nextCursor,
            Boolean hasNext,
            Integer size
    ) {
        List<MissionResDTO.MyMissionPreviewDTO> missions = userMissions.stream()
                .map(MissionConverter::toMyMissionPreviewDTO)
                .toList();

        return MissionResDTO.MyMissionListDTO.builder()
                .missions(missions)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .size(size)
                .build();
    }

    public static MissionResDTO.StartMissionResultDTO toStartMissionResultDTO(UserMission userMission) {
        return MissionResDTO.StartMissionResultDTO.builder()
                .userMissionId(userMission.getId())
                .status(userMission.getStatus())
                .startedAt(userMission.getStartedAt())
                .build();
    }

    public static MissionResDTO.CancelMissionResultDTO toCancelMissionResultDTO(UserMission userMission) {
        return MissionResDTO.CancelMissionResultDTO.builder()
                .userMissionId(userMission.getId())
                .status(userMission.getStatus())
                .canceledAt(userMission.getCanceledAt())
                .build();
    }

    public static MissionResDTO.VerificationRequestResultDTO toVerificationRequestResultDTO(
            UserMission userMission,
            String verificationCode,
            LocalDateTime expiresAt
    ) {
        return MissionResDTO.VerificationRequestResultDTO.builder()
                .userMissionId(userMission.getId())
                .verificationCode(verificationCode)
                .expiresAt(expiresAt)
                .build();
    }

    public static MissionResDTO.VerificationConfirmResultDTO toVerificationConfirmResultDTO(
            UserMission userMission,
            Integer earnedPoint
    ) {
        User user = userMission.getUser();

        return MissionResDTO.VerificationConfirmResultDTO.builder()
                .userMissionId(userMission.getId())
                .status(MissionStatus.COMPLETED)
                .earnedPoint(earnedPoint.longValue())
                .totalPoint(user.getPoint())
                .completedAt(userMission.getCompletedAt())
                .build();
    }

    private static Integer calculateDDay(LocalDate deadline) {
        return Math.toIntExact(ChronoUnit.DAYS.between(LocalDate.now(), deadline));
    }
}
