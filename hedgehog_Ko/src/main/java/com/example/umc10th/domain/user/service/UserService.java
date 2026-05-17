package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.foodCategory.entity.FoodCategory;
import com.example.umc10th.domain.foodCategory.exception.FoodCategoryException;
import com.example.umc10th.domain.foodCategory.exception.code.FoodCategoryErrorCode;
import com.example.umc10th.domain.foodCategory.repository.FoodCategoryRepository;
import com.example.umc10th.domain.term.entity.Term;
import com.example.umc10th.domain.term.exception.TermException;
import com.example.umc10th.domain.term.exception.code.TermErrorCode;
import com.example.umc10th.domain.term.repository.TermRepository;
import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.entity.mapping.UserPreferenceFood;
import com.example.umc10th.domain.user.entity.mapping.UserTerm;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserPreferenceFoodRepository;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.domain.user.repository.UserTermRepository;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserTermRepository userTermRepository;
    private final UserPreferenceFoodRepository userPreferenceFoodRepository;
    private final TermRepository termRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResDTO.SignupResultDTO signup(UserReqDTO.SignupDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserException(UserErrorCode.USER_ALREADY_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = UserConverter.toUser(request, encodedPassword);
        User savedUser = userRepository.saveAndFlush(user);

        savePreferenceFoods(savedUser, request.getFoodCategoryIds());
        saveUserTerms(savedUser, request.getTerms());

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

    private void savePreferenceFoods(User user, List<Long> foodCategoryIds) {
        Set<Long> requestedFoodCategoryIds = Set.copyOf(foodCategoryIds);
        List<FoodCategory> foodCategories = foodCategoryRepository.findAllById(requestedFoodCategoryIds);

        if (foodCategories.size() != requestedFoodCategoryIds.size()) {
            throw new FoodCategoryException(FoodCategoryErrorCode.FOOD_CATEGORY_NOT_FOUND);
        }

        List<UserPreferenceFood> userPreferenceFoods = foodCategories.stream()
                .map(foodCategory -> UserPreferenceFood.builder()
                        .user(user)
                        .foodCategory(foodCategory)
                        .build())
                .toList();

        userPreferenceFoodRepository.saveAll(userPreferenceFoods);
    }

    private void saveUserTerms(User user, List<UserReqDTO.TermAgreementDTO> termAgreements) {
        Map<Long, UserReqDTO.TermAgreementDTO> agreementMap = termAgreements.stream()
                .collect(Collectors.toMap(
                        UserReqDTO.TermAgreementDTO::getTermId,
                        Function.identity(),
                        (first, second) -> second,
                        LinkedHashMap::new
                ));

        List<Term> requestedTerms = termRepository.findAllById(agreementMap.keySet());
        if (requestedTerms.size() != agreementMap.size()) {
            throw new TermException(TermErrorCode.TERM_NOT_FOUND);
        }

        validateRequiredTermsAgreed(agreementMap);

        List<UserTerm> userTerms = requestedTerms.stream()
                .map(term -> UserTerm.builder()
                        .user(user)
                        .term(term)
                        .agreed(agreementMap.get(term.getId()).getAgreed())
                        .build())
                .toList();

        userTermRepository.saveAll(userTerms);
    }

    private void validateRequiredTermsAgreed(Map<Long, UserReqDTO.TermAgreementDTO> agreementMap) {
        List<Term> requiredTerms = termRepository.findAllByRequiredTrue();

        boolean allRequiredTermsAgreed = requiredTerms.stream()
                .allMatch(term -> {
                    UserReqDTO.TermAgreementDTO agreement = agreementMap.get(term.getId());
                    return agreement != null && Boolean.TRUE.equals(agreement.getAgreed());
                });

        if (!allRequiredTermsAgreed) {
            throw new TermException(TermErrorCode.REQUIRED_TERM_NOT_AGREED);
        }
    }
}
