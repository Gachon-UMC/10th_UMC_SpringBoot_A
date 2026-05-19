package com.example.umc10th.domain.auth.service;

import com.example.umc10th.domain.auth.converter.AuthConverter;
import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.auth.exception.AuthException;
import com.example.umc10th.domain.auth.exception.code.AuthErrorCode;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.enums.FoodCategory;
import com.example.umc10th.domain.member.enums.TermType;
import com.example.umc10th.domain.member.repository.FoodRepository;
import com.example.umc10th.domain.member.repository.MemberFoodRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.member.repository.MemberTermRepository;
import com.example.umc10th.domain.member.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final TermRepository termRepository;
    private final FoodRepository foodRepository;
    private final MemberTermRepository memberTermRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResDTO.SignUp signUp(AuthReqDTO.SignUp request) {
        // 1) 약관 동의 검증 (만 14세 + 필수 약관)
        validateAgreement(request.agree());

        // 2) 이메일 중복 검사
        if (memberRepository.existsByEmail(request.email())) {
            throw new AuthException(AuthErrorCode.EMAIL_DUPLICATED);
        }

        // 3) BCrypt 솔트 처리
        String encodedPassword = passwordEncoder.encode(request.password());

        // 4) Member 저장
        Member member = memberRepository.save(AuthConverter.toMember(request, encodedPassword));

        // 5) 동의한 약관 저장 (true 인 것만)
        saveAgreedTerms(member, request.agree());

        // 6) 선호 음식 저장
        saveFavoriteFoods(member, request.foodList());

        return AuthConverter.toSignUpResponse(member);
    }

    private void validateAgreement(AuthReqDTO.SignUp.Agree agree) {
        if (!Boolean.TRUE.equals(agree.age())) {
            throw new AuthException(AuthErrorCode.AGE_RESTRICTION);
        }
        if (!Boolean.TRUE.equals(agree.service()) || !Boolean.TRUE.equals(agree.privacy())) {
            throw new AuthException(AuthErrorCode.REQUIRED_TERMS_NOT_AGREED);
        }
    }

    private void saveAgreedTerms(Member member, AuthReqDTO.SignUp.Agree agree) {
        Map<TermType, Boolean> termMap = new EnumMap<>(TermType.class);
        termMap.put(TermType.AGE, agree.age());
        termMap.put(TermType.SERVICE, agree.service());
        termMap.put(TermType.PRIVACY, agree.privacy());
        termMap.put(TermType.LOCATION, agree.location());
        termMap.put(TermType.MARKETING, agree.marketing());

        termMap.forEach((type, agreed) -> {
            if (Boolean.TRUE.equals(agreed)) {
                Term term = termRepository.findByName(type)
                        .orElseThrow(() -> new AuthException(AuthErrorCode.TERM_NOT_FOUND));
                memberTermRepository.save(AuthConverter.toMemberTerm(member, term));
            }
        });
    }

    private void saveFavoriteFoods(Member member, List<FoodCategory> foodList) {
        for (FoodCategory category : foodList) {
            if (category == FoodCategory.NONE) continue;
            Food food = foodRepository.findByName(category)
                    .orElseThrow(() -> new AuthException(AuthErrorCode.FOOD_NOT_FOUND));
            memberFoodRepository.save(AuthConverter.toMemberFood(member, food));
        }
    }
}