package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional(readOnly = true)
    public MissionResDTO.MissionList getMyMissions(Long memberId, Boolean isComplete, int page, int size) {
        Page<MemberMission> missionPage = memberMissionRepository.findMyMissions(
                memberId, isComplete, PageRequest.of(page, size));
        return MissionConverter.toMissionList(missionPage);
    }

    @Transactional(readOnly = true)
    public MissionResDTO.MissionList getHomeMissions(Address address, int page, int size) {
        Page<Mission> missionPage = missionRepository.findAvailableMissionsByRegion(
                address, PageRequest.of(page, size));
        return MissionConverter.toHomeMissionList(missionPage);
    }
}