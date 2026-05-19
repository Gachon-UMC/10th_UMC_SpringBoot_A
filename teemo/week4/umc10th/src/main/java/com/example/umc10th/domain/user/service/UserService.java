package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.Food;
import com.example.umc10th.domain.user.entity.Term;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.entity.mapping.UserFood;
import com.example.umc10th.domain.user.entity.mapping.UserTerm;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.FoodRepository;
import com.example.umc10th.domain.user.repository.TermRepository;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final TermRepository termRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResDTO.JoinResult joinUser(UserReqDTO.Join request) {
        User newUser = UserConverter.toUser(request);
        newUser.encodePassword(passwordEncoder.encode(request.getPassword()));

        // 선호 음식 처리
        List<Food> foodList = request.getPreferCategory().stream()
                .map(foodRepository::findById)
                .map(optionalFood -> optionalFood.orElseThrow(() -> new RuntimeException("Food not found")))
                .toList();

        List<UserFood> userFoodList = UserConverter.toUserFoodList(foodList);
        userFoodList.forEach(userFood -> userFood.setMapping(newUser));

        // 약관 동의 처리
        List<Term> termList = request.getMemberTerms().stream()
                .map(termRepository::findById)
                .map(optionalTerm -> optionalTerm.orElseThrow(() -> new RuntimeException("Term not found")))
                .toList();

        List<UserTerm> userTermList = UserConverter.toUserTermList(termList);
        userTermList.forEach(userTerm -> userTerm.setMapping(newUser));

        return UserConverter.toJoinResultDTO(userRepository.save(newUser));
    }

    public UserResDTO.MyPage getMyPage(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        return UserConverter.toMyPageDTO(user);
    }
}
