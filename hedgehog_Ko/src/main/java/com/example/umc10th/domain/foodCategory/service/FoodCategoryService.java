package com.example.umc10th.domain.foodCategory.service;

import com.example.umc10th.domain.foodCategory.converter.FoodCategoryConverter;
import com.example.umc10th.domain.foodCategory.dto.FoodCategoryResDTO;
import com.example.umc10th.domain.foodCategory.entity.FoodCategory;
import com.example.umc10th.domain.foodCategory.repository.FoodCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    public FoodCategoryResDTO.FoodCategoryListDTO getFoodCategories() {
        List<FoodCategory> foodCategories = foodCategoryRepository.findAll();
        return FoodCategoryConverter.toFoodCategoryListDTO(foodCategories);
    }
}
