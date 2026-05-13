package com.umc10th.umc10th_kamang.domain.mission.converter;

import com.umc10th.umc10th_kamang.domain.category.entity.Region;
import com.umc10th.umc10th_kamang.domain.mission.dto.MissionResponse;
import com.umc10th.umc10th_kamang.domain.mission.entity.Mission;
import com.umc10th.umc10th_kamang.domain.mission.entity.Store;
import com.umc10th.umc10th_kamang.domain.mission.entity.UserMission;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.time.temporal.ChronoUnit;

public class MissionConverter {

    private MissionConverter() {
    }

    public static MissionResponse.HomeDTO toHomeDTO(
            Page<Mission> missions,
            Region region,
            Integer totalPoints,
            Long completedMissionCount,
            Integer missionGoalCount,
            Integer goalRewardPoints
    ) {
        List<MissionResponse.MissionPreviewDTO> missionDTOs = missions.getContent().stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .toList();

        return MissionResponse.HomeDTO.builder()
                .regionId(region.getId())
                .regionName(region.getName())
                .totalPoints(totalPoints)
                .completedMissionCount(completedMissionCount)
                .missionGoalCount(missionGoalCount)
                .goalRewardPoints(goalRewardPoints)
                .missions(missionDTOs)
                .currentPage(missions.getNumber())
                .totalPages(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .build();
    }

    public static MissionResponse.MissionListDTO toMissionListDTO(Page<UserMission> userMissions) {
        List<MissionResponse.UserMissionDTO> missionDTOs = userMissions.getContent().stream()
                .map(MissionConverter::toUserMissionDTO)
                .toList();

        return MissionResponse.MissionListDTO.builder()
                .missions(missionDTOs)
                .currentPage(userMissions.getNumber())
                .totalPages(userMissions.getTotalPages())
                .totalElements(userMissions.getTotalElements())
                .isFirst(userMissions.isFirst())
                .isLast(userMissions.isLast())
                .build();
    }

    private static MissionResponse.UserMissionDTO toUserMissionDTO(UserMission userMission) {
        Mission mission = userMission.getMission();
        Store store = mission.getStore();

        return MissionResponse.UserMissionDTO.builder()
                .userMissionId(userMission.getId())
                .storeName(store.getName())
                .storeImageUrl(store.getImageUrl())
                .condition(mission.getCondition())
                .rewardPoints(mission.getRewardPoints())
                .status(userMission.getStatus())
                .build();
    }

    private static MissionResponse.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        Store store = mission.getStore();

        return MissionResponse.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .storeName(store.getName())
                .storeImageUrl(store.getImageUrl())
                .condition(mission.getCondition())
                .rewardPoints(mission.getRewardPoints())
                .deadline(mission.getDeadline())
                .dDay(calculateDday(mission))
                .build();
    }

    private static Long calculateDday(Mission mission) {
        long dDay = ChronoUnit.DAYS.between(LocalDate.now(), mission.getDeadline().toLocalDate());
        return Math.max(dDay, 0);
    }
}
