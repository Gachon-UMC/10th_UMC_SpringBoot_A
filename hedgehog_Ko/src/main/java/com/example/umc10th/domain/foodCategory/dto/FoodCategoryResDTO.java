package com.example.umc10th.domain.foodCategory.dto;

import lombok.Builder;

import java.util.List;

public class FoodCategoryResDTO {

    @Builder
    public record FoodCategoryListDTO(
            List<FoodCategoryDTO> foodCategories
    ) {
    }

    @Builder
    public record FoodCategoryDTO(
            Long foodCategoryId,
            String foodCategoryName
    ) {
    }
}
