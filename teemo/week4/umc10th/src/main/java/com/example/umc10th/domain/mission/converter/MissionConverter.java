package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(UserMission userMission) {
        return MissionResDTO.MissionPreviewDTO.builder()
                .missionId(userMission.getMission().getId())
                .storeName(userMission.getMission().getStore().getName())
                .missionSpec(userMission.getMission().getConditional())
                .reward(userMission.getMission().getPoint())
                .createdAt(userMission.getCreatedAt())
                .build();
    }

    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .missionSpec(mission.getConditional())
                .reward(mission.getPoint())
                .deadline(mission.getDeadline().atStartOfDay()) // LocalDate -> LocalDateTime
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<UserMission> userMissionPage) {
        List<MissionResDTO.MissionPreviewDTO> missionPreviewDTOList = userMissionPage.getContent().stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .collect(Collectors.toList());

        return MissionResDTO.MissionPreviewListDTO.builder()
                .isLast(userMissionPage.isLast())
                .isFirst(userMissionPage.isFirst())
                .totalPage(userMissionPage.getTotalPages())
                .totalElements(userMissionPage.getTotalElements())
                .listSize(missionPreviewDTOList.size())
                .missionList(missionPreviewDTOList)
                .build();
    }

    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTOFromMission(Page<Mission> missionPage) {
        List<MissionResDTO.MissionPreviewDTO> missionPreviewDTOList = missionPage.getContent().stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .collect(Collectors.toList());

        return MissionResDTO.MissionPreviewListDTO.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionPreviewDTOList.size())
                .missionList(missionPreviewDTOList)
                .build();
    }
}
