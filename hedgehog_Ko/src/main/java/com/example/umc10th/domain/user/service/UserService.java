package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResDTO.SignupResultDTO signup(UserReqDTO.SignupDTO request) {
        User user = UserConverter.toUser(request);
        User savedUser = userRepository.saveAndFlush(user);

        return UserConverter.toSignupResultDTO(savedUser);
    }

    public UserResDTO.MyInfoDTO getMyInfo(Long userId) {
        User user = userRepository.findByIdAndDeletedAtIsNull(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserConverter.toMyInfoDTO(user);
    }

    @Transactional
    public UserResDTO.UpdateMyInfoResultDTO updateMyInfo(
            Long userId,
            UserReqDTO.UpdateMyInfoDTO request
    ) {
        User user = getActiveUser(userId);
        user.updateMyInfo(
                request.nickname(),
                request.address(),
                request.detailAddress(),
                request.profileImageUrl()
        );

        User savedUser = userRepository.saveAndFlush(user);

        return UserConverter.toUpdateMyInfoResultDTO(savedUser);
    }

    @Transactional
    public UserResDTO.DeleteUserResultDTO deleteMyAccount(Long userId) {
        User user = getActiveUser(userId);
        user.delete();

        User savedUser = userRepository.saveAndFlush(user);

        return UserConverter.toDeleteUserResultDTO(savedUser);
    }

    public UserResDTO.PointDTO getMyPoint(Long userId) {
        User user = getActiveUser(userId);
        return UserConverter.toPointDTO(user);
    }

    private User getActiveUser(Long userId) {
        return userRepository.findByIdAndDeletedAtIsNull(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
    }
}
