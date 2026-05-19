package com.example.umc10th.domain.mission.service;


import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    public MissionResDTO.Pagination<MissionResDTO.MissionInfo> getMyMissions(
            Long memberId,
            Status status,
            Pageable pageable
    ) {
        Page<MissionResDTO.MissionInfo> page = memberMissionRepository
                .findByMemberIdAndStatus(memberId, status, pageable)
                .map(MissionConverter::toMissionInfo);

        return MissionConverter.toPagination(
                page.getContent(),
                page.getNumber(),
                page.getSize()
        );

    }

    public MissionResDTO.HomeDTO getHome(Long memberId, Pageable pageable) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));

        Page<MemberMission> memberMissions = memberMissionRepository
                .findByMemberIdAndStatus(memberId, Status.NOT_STARTED, pageable);

       long completedCount = memberMissionRepository
                .countByMemberIdAndStatus(memberId, Status.SUCCESS);

        Page<MissionResDTO.MissionInfo> missionInfos = memberMissions.map(MissionConverter::toMissionInfo);

        return MissionConverter.toHomeDTO(member, completedCount, missionInfos);
    }

    @Transactional
    public MissionResDTO.GetMission createMission(
            Long storeId,
            MissionReqDTO.CreateMission dto
    ) {
        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()-> new StoreException(StoreErrorCode.NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toMission(store, dto);

        // 미션 DB 저장
        missionRepository.save(mission);
        return MissionConverter.toGetMission(mission);
    }

    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
            ) {
        // 가게 내 미션들 조회
        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        } else{
            sortInfo = Sort.by("id").descending();
        }

//        페이지 정보들을 PageRequest 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

//        가게 내 미션들 조회
        Page<Mission> missionList = missionRepository.findByStoreId(storeId, pageRequest);


//        미션들 응답 DTO로 포장하기
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).getContent(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }
}
