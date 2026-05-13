package com.umc10th.umc10th_kamang.domain.user.service;

import com.umc10th.umc10th_kamang.domain.user.converter.UserConverter;
import com.umc10th.umc10th_kamang.domain.user.dto.UserResponse;
import com.umc10th.umc10th_kamang.domain.user.entity.User;
import com.umc10th.umc10th_kamang.domain.user.exception.UserErrorCode;
import com.umc10th.umc10th_kamang.domain.user.exception.UserException;
import com.umc10th.umc10th_kamang.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserResponse.MyPageDTO getMyPage(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserConverter.toMyPageDTO(user);
    }
}
