package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.store.entity.Location;
import com.example.umc10th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    // 가게 미션 생성
    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ){
        return Mission.builder()
                .store(store)
                .content(dto.content())
                .point(dto.point())
                .dueDate(dto.dueDate())
                .build();
    }

//    가게 내 미션 조회
    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ){
        return MissionResDTO.GetMission.builder()
                .content(mission.getContent())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

//  미션 정보 조회
    public static MissionResDTO.MissionInfo toMissionInfo(
            MemberMission mm
    ) {
        return new MissionResDTO.MissionInfo(
                mm.getMission().getStore().getStoreName(),
                mm.getMission().getStore().getStoreCategory(),
                mm.getMission().getContent(),
                mm.getMission().getDueDate(),
                mm.getMission().getPoint(),
                MissionResDTO.MissionStatus.valueOf(mm.getStatus().name())
        );
    }

    // 홈 화면 조회
    public static MissionResDTO.HomeDTO toHomeDTO(
            Member member,
            long completedCount,
            Page<MissionResDTO.MissionInfo> missionInfos

    ){
        return MissionResDTO.HomeDTO.builder()
                .locationName(member.getDetailAddress())
                .totalPoint(member.getTotalPoint())
                .completedMissionCount((int) completedCount)
                .missions(missionInfos.getContent())
                .build();
    }

//    페이지네이션 틀 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}