package com.example.umc10th.domain.mission.service;


import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public Page<MissionResDTO.MissionInfo> getMyMissions(
            Long memberId,
            Status status,
            Pageable pageable
    ) {
        return memberMissionRepository
                .findByMemberIdAndStatus(memberId, status, pageable)
                .map(mm -> new MissionResDTO.MissionInfo(
                        mm.getMission().getStore().getStoreName(),
                        mm.getMission().getStore().getStoreCategory(),
                        mm.getMission().getContent(),
                        mm.getMission().getDueDate(),
                        mm.getMission().getPoint(),
                        MissionResDTO.MissionStatus.valueOf(mm.getStatus().name())
                ));
    }

    public MissionResDTO.HomeDTO getHome(Long memberId, Pageable pageable) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));

        Page<MemberMission> memberMissions = memberMissionRepository
                .findByMemberIdAndStatus(memberId, Status.NOT_STARTED, pageable);

       long completedCount = memberMissionRepository
                .countByMemberIdAndStatus(memberId, Status.SUCCESS);

        Page<MissionResDTO.MissionInfo> missionInfos = memberMissions.map(mm ->
                new MissionResDTO.MissionInfo(
                        mm.getMission().getStore().getStoreName(),
                        mm.getMission().getStore().getStoreCategory(),
                        mm.getMission().getContent(),
                        mm.getMission().getDueDate(),
                        mm.getMission().getPoint(),
                        MissionResDTO.MissionStatus.valueOf(mm.getStatus().name())
                )
        );

        return MissionResDTO.HomeDTO.builder()
                .locationName(member.getDetailAddress())
                .totalPoint(member.getTotalPoint())
                .completedMissionCount((int) completedCount)
                .missions(missionInfos.getContent())
                .build();
    }
}
