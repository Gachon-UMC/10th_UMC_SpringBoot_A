package com.example.umc10th.domain.foodCategory.converter;

import com.example.umc10th.domain.foodCategory.dto.FoodCategoryResDTO;
import com.example.umc10th.domain.foodCategory.entity.FoodCategory;

import java.util.List;

public class FoodCategoryConverter {

    public static FoodCategoryResDTO.FoodCategoryDTO toFoodCategoryDTO(FoodCategory foodCategory) {
        return FoodCategoryResDTO.FoodCategoryDTO.builder()
                .foodCategoryId(foodCategory.getId())
                .foodCategoryName(foodCategory.getName())
                .build();
    }

    public static FoodCategoryResDTO.FoodCategoryListDTO toFoodCategoryListDTO(
            List<FoodCategory> foodCategories
    ) {
        return FoodCategoryResDTO.FoodCategoryListDTO.builder()
                .foodCategories(foodCategories.stream()
                        .map(FoodCategoryConverter::toFoodCategoryDTO)
                        .toList())
                .build();
    }
}
