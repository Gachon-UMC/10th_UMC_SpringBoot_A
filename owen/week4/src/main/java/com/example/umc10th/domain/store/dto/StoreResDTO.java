package com.example.umc10th.domain.store.dto;

import lombok.Builder;

public class StoreResDTO {

    // GET /api/stores/{storeId}
    @Builder
    public record StoreInfo(
            Long storeId,
            String name,
            String address,
            String category,
            Float averageRating,
            Integer reviewCount
    ) {}
}