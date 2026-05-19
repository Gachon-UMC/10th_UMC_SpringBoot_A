package com.example.umc10th.global.config;

import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.enums.FoodCategory;
import com.example.umc10th.domain.member.enums.TermType;
import com.example.umc10th.domain.member.repository.FoodRepository;
import com.example.umc10th.domain.member.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * [8주차] Term / Food 마스터 데이터 시드.
 * 회원가입 API 가 termRepository.findByName / foodRepository.findByName 으로 마스터 row 를 찾기 때문에
 * 빈 DB 에서 가입이 TERM_NOT_FOUND / FOOD_NOT_FOUND 로 실패하지 않도록 미리 채워둠. (idempotent)
 */
@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final TermRepository termRepository;
    private final FoodRepository foodRepository;

    @Bean
    public ApplicationRunner seedMasterData() {
        return args -> {
            // Term — AGE, SERVICE, PRIVACY, LOCATION, MARKETING
            Arrays.stream(TermType.values())
                    .filter(type -> termRepository.findByName(type).isEmpty())
                    .forEach(type -> termRepository.save(Term.builder().name(type).build()));

            // Food — NONE, KOREAN, ENGLISH
            Arrays.stream(FoodCategory.values())
                    .filter(cat -> foodRepository.findByName(cat).isEmpty())
                    .forEach(cat -> foodRepository.save(Food.builder().name(cat).build()));
        };
    }
}