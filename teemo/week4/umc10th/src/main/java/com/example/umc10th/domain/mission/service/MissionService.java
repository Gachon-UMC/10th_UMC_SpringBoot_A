package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.store.enums.Address;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
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

    public MissionResDTO.MissionPreviewList getMissionListByLocation(Address address, Integer page) {
        Page<Mission> missionPage = missionRepository.findAllByLocationName(address, PageRequest.of(page, 10));
        return MissionConverter.toMissionPreviewListFromMission(missionPage);
    }

    public MissionResDTO.MissionPreviewList getMyMissionList(Long userId, Boolean isCompleted, Integer page) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Page<UserMission> userMissionPage = userMissionRepository.findAllByUserAndIsComplete(user, isCompleted, PageRequest.of(page, 10));
        return MissionConverter.toMissionPreviewList(userMissionPage);
    }
}
