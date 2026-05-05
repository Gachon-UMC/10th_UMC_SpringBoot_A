package com.example.umc10th.domain.store.dto;

import lombok.Builder;

import java.util.List;

public class StoreResDTO {

    @Builder
    public record StoreListDTO(
            List<StorePreviewDTO> stores,
            Long nextCursor,
            Boolean hasNext,
            Integer size
    ) {
    }

    @Builder
    public record StorePreviewDTO(
            Long storeId,
            String storeName,
            Long foodCategoryId,
            String foodCategoryName,
            String detailAddress, // WF 상에는 주소가 안 보이지만, 지도에서는 주소로 찍혀야 해서.. 애매함.. 잘 모르겠음..
            Double distance,
            String thumbnailImageUrl
    ) {
    }

    @Builder
    public record StoreDetailDTO(
            Long storeId,
            String storeName,
            String detailAddress,
            Long foodCategoryId,
            String foodCategoryName,
            Boolean open, // 영업 중인지 아닌지
            String thumbnailImageUrl,
            Double averageStar,
            Integer reviewCount,
            List<String> photoUrls,
            List<ReviewPreviewDTO> reviews
    ){}

    @Builder
    public record ReviewPreviewDTO(
            Long reviewId,
            String userName,
            Double star,
            String content,
            List<String> photoUrls
    ) {
    }
}
