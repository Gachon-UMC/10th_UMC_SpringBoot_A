package com.example.umc10th.domain.foodCategory.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FoodCategorySuccessCode implements BaseSuccessCode {
    GET_FOOD_CATEGORY_LIST_SUCCESS(
            HttpStatus.OK,
            "FOOD200_1",
            "음식 카테고리 목록 조회에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
