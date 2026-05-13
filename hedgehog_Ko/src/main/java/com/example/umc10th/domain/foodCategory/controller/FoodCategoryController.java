package com.example.umc10th.domain.foodCategory.controller;

import com.example.umc10th.domain.foodCategory.dto.FoodCategoryResDTO;
import com.example.umc10th.domain.foodCategory.exception.code.FoodCategorySuccessCode;
import com.example.umc10th.domain.foodCategory.service.FoodCategoryService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/foodCategory")
public class FoodCategoryController {

    private final FoodCategoryService foodCategoryService;

    @GetMapping
    public ApiResponse<FoodCategoryResDTO.FoodCategoryListDTO> getFoodCategories() {
        FoodCategoryResDTO.FoodCategoryListDTO response = foodCategoryService.getFoodCategories();
        return ApiResponse.onSuccess(FoodCategorySuccessCode.GET_FOOD_CATEGORY_LIST_SUCCESS, response);
    }
}
