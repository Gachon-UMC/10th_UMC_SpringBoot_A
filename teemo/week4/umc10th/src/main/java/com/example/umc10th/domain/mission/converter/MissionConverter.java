package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionPreview toMissionPreview(UserMission userMission) {
        return MissionResDTO.MissionPreview.builder()
                .missionId(userMission.getMission().getId())
                .storeName(userMission.getMission().getStore().getName())
                .missionSpec(userMission.getMission().getConditional())
                .reward(userMission.getMission().getPoint())
                .createdAt(userMission.getCreatedAt())
                .build();
    }

    public static MissionResDTO.MissionPreview toMissionPreview(Mission mission) {
        return MissionResDTO.MissionPreview.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .missionSpec(mission.getConditional())
                .reward(mission.getPoint())
                .deadline(mission.getDeadline().atStartOfDay()) // LocalDate -> LocalDateTime
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static MissionResDTO.MissionPreviewList toMissionPreviewList(Page<UserMission> userMissionPage) {
        List<MissionResDTO.MissionPreview> missionPreviewDTOList = userMissionPage.getContent().stream()
                .map(MissionConverter::toMissionPreview)
                .collect(Collectors.toList());

        return MissionResDTO.MissionPreviewList.builder()
                .isLast(userMissionPage.isLast())
                .isFirst(userMissionPage.isFirst())
                .totalPage(userMissionPage.getTotalPages())
                .totalElements(userMissionPage.getTotalElements())
                .listSize(missionPreviewDTOList.size())
                .missionList(missionPreviewDTOList)
                .build();
    }

    public static MissionResDTO.MissionPreviewList toMissionPreviewListFromMission(Page<Mission> missionPage) {
        List<MissionResDTO.MissionPreview> missionPreviewDTOList = missionPage.getContent().stream()
                .map(MissionConverter::toMissionPreview)
                .collect(Collectors.toList());

        return MissionResDTO.MissionPreviewList.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionPreviewDTOList.size())
                .missionList(missionPreviewDTOList)
                .build();
    }
}
