package com.example.umc10th.domain.foodCategory.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FoodCategoryErrorCode implements BaseErrorCode {

    FOOD_CATEGORY_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "FOOD404_1",
            "음식 카테고리를 찾을 수 없습니다."
    ),
    INVALID_FOOD_CATEGORY_REQUEST(
            HttpStatus.BAD_REQUEST,
            "FOOD400_1",
            "음식 카테고리 요청 값이 올바르지 않습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
