package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.enums.Agreement;
import com.example.umc10th.domain.member.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final FoodRepository foodRepository ;
    private final TermRepository termRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final MemberTermRepository memberTermRepository;

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));
    }

    @Transactional
    public Member signUp(MemberReqDTO.SignUp request) {

        // 1. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.password());

        // 2. Member 엔티티 생성
        Member member = Member.builder()
                .name(request.name())
                .email(request.email())
                .password(encodedPassword)
                .gender(request.gender())
                .birth(request.birthDate())
                .detailAddress(request.detailAddress())
                .build();

        // 3. 저장
        memberRepository.save(member);

        // 4. 선호 음식 매핑 처리
        for(Long foodId:request.preferredFoods()){
            Food food = foodRepository.findById(foodId)
                    .orElseThrow(()-> new RuntimeException("음식을 찾을 수 없습니다."));

            MemberFood memberFood = MemberFood.builder()
                    .member(member)
                    .food(food)
                    .build();
            memberFoodRepository.save(memberFood);
        }

        // 5. 약관 매핑 처리
        for(Long termId: request.terms()){
            Term term = termRepository.findById(termId)
                    .orElseThrow(()->new RuntimeException("이용 약관을 찾을 수 없습니다."));

            MemberTerm memberTerm = MemberTerm.builder()
                    .member(member)
                    .term(term)
                    .agreement(Agreement.AGREED)
                    .build();
            memberTermRepository.save(memberTerm);
        }

        return member;
    }


}
