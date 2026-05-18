package com.umc10th.umc10th_kamang.domain.user.service;

import com.umc10th.umc10th_kamang.domain.category.entity.FoodCategory;
import com.umc10th.umc10th_kamang.domain.category.entity.UserFoodPreference;
import com.umc10th.umc10th_kamang.domain.category.exception.CategoryErrorCode;
import com.umc10th.umc10th_kamang.domain.category.exception.CategoryException;
import com.umc10th.umc10th_kamang.domain.category.repository.FoodCategoryRepository;
import com.umc10th.umc10th_kamang.domain.category.repository.UserFoodPreferenceRepository;
import com.umc10th.umc10th_kamang.domain.user.converter.UserConverter;
import com.umc10th.umc10th_kamang.domain.user.dto.UserRequest;
import com.umc10th.umc10th_kamang.domain.user.dto.UserResponse;
import com.umc10th.umc10th_kamang.domain.user.entity.Terms;
import com.umc10th.umc10th_kamang.domain.user.entity.User;
import com.umc10th.umc10th_kamang.domain.user.entity.UserTermAgreement;
import com.umc10th.umc10th_kamang.domain.user.enums.SocialType;
import com.umc10th.umc10th_kamang.domain.user.exception.UserErrorCode;
import com.umc10th.umc10th_kamang.domain.user.exception.UserException;
import com.umc10th.umc10th_kamang.domain.user.repository.TermsRepository;
import com.umc10th.umc10th_kamang.domain.user.repository.UserRepository;
import com.umc10th.umc10th_kamang.domain.user.repository.UserTermAgreementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final TermsRepository termsRepository;
    private final UserTermAgreementRepository userTermAgreementRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final UserFoodPreferenceRepository userFoodPreferenceRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse.SignupResultDTO signup(UserRequest.SignupDTO request) {
        // 폼 로그인에서는 이메일이 로그인 ID가 되므로 중복 가입 방지
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserException(UserErrorCode.DUPLICATE_EMAIL);
        }

        // 요청에 포함된 약관 ID를 실제 약관 엔티티로 조회해 필수 약관 동의 여부 검증
        Map<Long, Terms> termsById = termsRepository.findAllById(
                        request.getTermAgreements().stream()
                                .map(UserRequest.TermAgreementDTO::getTermId)
                                .toList()
                ).stream()
                .collect(Collectors.toMap(Terms::getId, Function.identity()));

        validateTermAgreements(request, termsById);

        // 로컬 회원가입 사용자는 socialId 제약을 만족시키기 위해 email을 socialId로도 저장
        User user = userRepository.save(User.builder()
                .socialType(SocialType.LOCAL)
                .socialId(request.getEmail())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .gender(request.getGender())
                .birthDate(LocalDate.parse(request.getBirthDate()))
                .address(request.getAddress())
                .addressDetail(request.getAddressDetail())
                .build());

        // 사용자 저장 이후 연관 데이터인 약관 동의와 음식 선호도 저장
        saveTermAgreements(request, termsById, user);
        saveFoodPreferences(request, user);

        return UserConverter.toSignupResultDTO(user);
    }

    public UserResponse.MyPageDTO getMyPage(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserConverter.toMyPageDTO(user);
    }

    private void validateTermAgreements(
            UserRequest.SignupDTO request,
            Map<Long, Terms> termsById
    ) {
        if (termsById.size() != request.getTermAgreements().size()) {
            throw new UserException(UserErrorCode.TERMS_NOT_FOUND);
        }

        // 필수 약관 중 하나라도 동의하지 않았다면 회원가입 거부
        Map<Long, Boolean> agreementByTermId = request.getTermAgreements().stream()
                .collect(Collectors.toMap(
                        UserRequest.TermAgreementDTO::getTermId,
                        UserRequest.TermAgreementDTO::getIsAgreed
                ));

        boolean hasMissingRequiredAgreement = termsById.values().stream()
                .anyMatch(term -> Boolean.TRUE.equals(term.getIsRequired())
                        && !Boolean.TRUE.equals(agreementByTermId.get(term.getId())));

        if (hasMissingRequiredAgreement) {
            throw new UserException(UserErrorCode.REQUIRED_TERM_NOT_AGREED);
        }
    }

    private void saveTermAgreements(
            UserRequest.SignupDTO request,
            Map<Long, Terms> termsById,
            User user
    ) {
        request.getTermAgreements().forEach(agreement ->
                userTermAgreementRepository.save(UserTermAgreement.builder()
                        .user(user)
                        .term(termsById.get(agreement.getTermId()))
                        .isAgreed(agreement.getIsAgreed())
                        .build())
        );
    }

    private void saveFoodPreferences(UserRequest.SignupDTO request, User user) {
        request.getFoodCategoryIds().forEach(foodCategoryId -> {
            // 존재하지 않는 음식 카테고리가 포함되면 잘못된 가입 요청으로 처리
            FoodCategory foodCategory = foodCategoryRepository.findById(foodCategoryId)
                    .orElseThrow(() -> new CategoryException(CategoryErrorCode.FOOD_CATEGORY_NOT_FOUND));

            userFoodPreferenceRepository.save(UserFoodPreference.builder()
                    .user(user)
                    .foodCategory(foodCategory)
                    .build());
        });
    }
}
