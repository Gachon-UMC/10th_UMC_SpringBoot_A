package com.example.umc10th.domain.foodCategory.controller;

import com.example.umc10th.domain.foodCategory.dto.FoodCategoryResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "FoodCategory", description = "음식 카테고리 조회 API")
public interface FoodCategoryControllerDocs {

    @Operation(
            summary = "음식 카테고리 목록 조회",
            description = "가게 분류와 사용자 선호 음식 선택에 사용할 음식 카테고리 목록을 조회합니다."
    )
    ApiResponse<FoodCategoryResDTO.FoodCategoryListDTO> getFoodCategories();
}
