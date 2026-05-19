package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.review.entity.Review;
import java.util.List;

public class StoreConverter {

    public static StoreResDTO.StoreInfo toStoreInfo(Store store, List<Review> reviews) {
        int reviewCount = reviews.size();
        Float average = reviewCount > 0
                ? (float) reviews.stream().mapToDouble(Review::getStar).average().orElse(0)
                : 0f;

        return new StoreResDTO.StoreInfo(
                store.getId(),
                store.getName(),
                store.getDetailAddress(),
                store.getName(),
                average,
                reviewCount
        );
    }
}