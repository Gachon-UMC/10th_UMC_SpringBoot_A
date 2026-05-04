package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;

public class StoreConverter {

    public static StoreResDTO.StoreInfo toStoreInfo(Store store) {
        Float avgRating = store.getReviewList().stream()
                .map(r -> r.getStar())
                .reduce(0f, Float::sum);
        int reviewCount = store.getReviewList().size();
        Float average = reviewCount > 0 ? avgRating / reviewCount : 0f;

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