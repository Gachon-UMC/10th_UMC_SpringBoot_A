package com.umc10th.umc10th_kamang.domain.mission.converter;

import com.umc10th.umc10th_kamang.domain.mission.dto.MissionResponse;
import com.umc10th.umc10th_kamang.domain.mission.entity.Mission;
import com.umc10th.umc10th_kamang.domain.mission.entity.Store;
import com.umc10th.umc10th_kamang.domain.mission.entity.UserMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    private MissionConverter() {
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
}
