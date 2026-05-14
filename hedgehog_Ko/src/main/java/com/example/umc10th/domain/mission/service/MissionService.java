package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.entity.mapping.UserMission;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserMissionRepository;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private static final String TEMP_VERIFICATION_CODE = "482913";
    private static final int VERIFICATION_EXPIRE_MINUTES = 10;

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    public MissionResDTO.MyMissionListDTO getMyMissions(
            Long userId,
            Long regionId,
            MissionStatus status,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {
        if (!userRepository.existsByIdAndDeletedAtIsNull(userId)) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        validatePage(pageSize, pageNumber);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, getSort(sort));

        Page<UserMission> userMissions = userMissionRepository.findMyMissions(
                userId,
                regionId,
                status,
                pageRequest
        );

        return MissionConverter.toMyMissionListDTO(
                userMissions.getContent(),
                userMissions.getNumber(),
                userMissions.getSize(),
                userMissions.hasNext()
        );
    }

    @Transactional
    public MissionResDTO.StartMissionResultDTO startMission(
            Long userId,
            Long userMissionId
    ) {
        UserMission userMission = getMyUserMission(userId, userMissionId);

        validateStartable(userMission);

        userMission.start();
        UserMission savedUserMission = userMissionRepository.saveAndFlush(userMission);

        return MissionConverter.toStartMissionResultDTO(savedUserMission);
    }

    @Transactional
    public MissionResDTO.CancelMissionResultDTO cancelMission(
            Long userId,
            Long userMissionId
    ) {
        UserMission userMission = getMyUserMission(userId, userMissionId);

        validateCancelable(userMission);

        userMission.cancel();
        UserMission savedUserMission = userMissionRepository.saveAndFlush(userMission);

        return MissionConverter.toCancelMissionResultDTO(savedUserMission);
    }

    public MissionResDTO.VerificationRequestResultDTO requestMissionVerification(
            Long userId,
            Long userMissionId
    ) {
        UserMission userMission = getMyUserMission(userId, userMissionId);

        validateVerifiable(userMission);

        return MissionConverter.toVerificationRequestResultDTO(
                userMission,
                TEMP_VERIFICATION_CODE,
                LocalDateTime.now().plusMinutes(VERIFICATION_EXPIRE_MINUTES)
        );
    }

    @Transactional
    public MissionResDTO.VerificationConfirmResultDTO confirmMissionVerification(
            Long userId,
            Long userMissionId
    ) {
        UserMission userMission = getMyUserMission(userId, userMissionId);

        validateVerifiable(userMission);

        Integer earnedPoint = userMission.getMission().getPoint();
        User user = userMission.getUser();

        userMission.complete();
        user.addPoint(earnedPoint);

        UserMission savedUserMission = userMissionRepository.saveAndFlush(userMission);
        userRepository.saveAndFlush(user);

        return MissionConverter.toVerificationConfirmResultDTO(savedUserMission, earnedPoint);
    }

    private UserMission getMyUserMission(Long userId, Long userMissionId) {
        if (!userRepository.existsByIdAndDeletedAtIsNull(userId)) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        UserMission userMission = userMissionRepository.findActiveUserMissionById(userMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.USER_MISSION_NOT_FOUND));

        if (!userMission.getUser().getId().equals(userId)) {
            throw new MissionException(MissionErrorCode.FORBIDDEN_USER_MISSION);
        }

        return userMission;
    }

    private void validateStartable(UserMission userMission) {
        MissionStatus status = userMission.getStatus();

        if (status == MissionStatus.ASSIGNED) {
            return;
        }
        if (status == MissionStatus.CHALLENGING) {
            throw new MissionException(MissionErrorCode.ALREADY_CHALLENGING_MISSION);
        }
        if (status == MissionStatus.COMPLETED) {
            throw new MissionException(MissionErrorCode.ALREADY_COMPLETED_MISSION);
        }
        if (status == MissionStatus.CANCELED) {
            throw new MissionException(MissionErrorCode.ALREADY_CANCELED_MISSION);
        }
        if (status == MissionStatus.EXPIRED) {
            throw new MissionException(MissionErrorCode.EXPIRED_MISSION);
        }

        throw new MissionException(MissionErrorCode.INVALID_MISSION_STATUS);
    }

    private void validateCancelable(UserMission userMission) {
        MissionStatus status = userMission.getStatus();

        if (status == MissionStatus.ASSIGNED || status == MissionStatus.CHALLENGING) {
            return;
        }
        if (status == MissionStatus.COMPLETED) {
            throw new MissionException(MissionErrorCode.ALREADY_COMPLETED_MISSION);
        }
        if (status == MissionStatus.CANCELED) {
            throw new MissionException(MissionErrorCode.ALREADY_CANCELED_MISSION);
        }
        if (status == MissionStatus.EXPIRED) {
            throw new MissionException(MissionErrorCode.EXPIRED_MISSION);
        }

        throw new MissionException(MissionErrorCode.INVALID_MISSION_STATUS);
    }

    private void validateVerifiable(UserMission userMission) {
        MissionStatus status = userMission.getStatus();

        if (status == MissionStatus.CHALLENGING) {
            return;
        }
        if (status == MissionStatus.ASSIGNED) {
            throw new MissionException(MissionErrorCode.MISSION_VERIFICATION_NOT_REQUESTED);
        }
        if (status == MissionStatus.COMPLETED) {
            throw new MissionException(MissionErrorCode.ALREADY_COMPLETED_MISSION);
        }
        if (status == MissionStatus.CANCELED) {
            throw new MissionException(MissionErrorCode.ALREADY_CANCELED_MISSION);
        }
        if (status == MissionStatus.EXPIRED) {
            throw new MissionException(MissionErrorCode.EXPIRED_MISSION);
        }

        throw new MissionException(MissionErrorCode.INVALID_MISSION_STATUS);
    }

    private void validatePage(Integer pageSize, Integer pageNumber) {
        if (pageSize == null || pageSize <= 0 || pageNumber == null || pageNumber < 0) {
            throw new MissionException(MissionErrorCode.INVALID_MISSION_PAGE);
        }
    }

    private Sort getSort(String sort) {
        if (sort == null || sort.isBlank()) {
            return Sort.by(Sort.Direction.DESC, "id");
        }

        String[] sortTokens = sort.split(",");
        String property = sortTokens[0].trim();
        Sort.Direction direction = Sort.Direction.DESC;

        if (sortTokens.length > 1) {
            direction = Sort.Direction.fromOptionalString(sortTokens[1].trim())
                    .orElseThrow(() -> new MissionException(MissionErrorCode.INVALID_MISSION_SORT));
        }

        if (!property.equals("id")
                && !property.equals("deadline")
                && !property.equals("createdAt")
                && !property.equals("updatedAt")) {
            throw new MissionException(MissionErrorCode.INVALID_MISSION_SORT);
        }

        return Sort.by(direction, property);
    }
}
