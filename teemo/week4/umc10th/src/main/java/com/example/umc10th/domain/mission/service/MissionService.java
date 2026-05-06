package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.store.enums.Address;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;

    public Page<Mission> getMissionListByLocation(Address address, Integer page) {
        return missionRepository.findAllByLocationName(address, PageRequest.of(page, 10));
    }

    public Page<UserMission> getMyMissionList(Long userId, Boolean isCompleted, Integer page) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        return userMissionRepository.findAllByUserAndIsComplete(user, isCompleted, PageRequest.of(page, 10));
    }
}
