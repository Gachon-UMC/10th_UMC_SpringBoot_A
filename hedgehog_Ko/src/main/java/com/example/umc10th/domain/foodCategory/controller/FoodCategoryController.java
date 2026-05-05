package com.example.umc10th.domain.foodCategory.controller;

import com.example.umc10th.domain.foodCategory.dto.FoodCategoryResDTO;
import com.example.umc10th.domain.foodCategory.exception.code.FoodCategorySuccessCode;
import com.example.umc10th.domain.foodCategory.service.FoodCategoryService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/foodCategory")
public class FoodCategoryController {

    private final FoodCategoryService foodCategoryService;

    @GetMapping
    public ApiResponse<FoodCategoryResDTO.FoodCategoryListDTO> getFoodCategories() {
        List<FoodCategoryResDTO.FoodCategoryDTO> foodCategories = List.of(
                FoodCategoryResDTO.FoodCategoryDTO.builder()
                        .foodCategoryId(1L)
                        .foodCategoryName("한식")
                        .build(),
                FoodCategoryResDTO.FoodCategoryDTO.builder()
                        .foodCategoryId(2L)
                        .foodCategoryName("중식")
                        .build(),
                FoodCategoryResDTO.FoodCategoryDTO.builder()
                        .foodCategoryId(3L)
                        .foodCategoryName("일식")
                        .build(),
                FoodCategoryResDTO.FoodCategoryDTO.builder()
                        .foodCategoryId(4L)
                        .foodCategoryName("카페")
                        .build()
        );

        FoodCategoryResDTO.FoodCategoryListDTO response = FoodCategoryResDTO.FoodCategoryListDTO.builder()
                .foodCategories(foodCategories)
                .build();

        return ApiResponse.onSuccess(FoodCategorySuccessCode.GET_FOOD_CATEGORY_LIST_SUCCESS, response);
    }
}
