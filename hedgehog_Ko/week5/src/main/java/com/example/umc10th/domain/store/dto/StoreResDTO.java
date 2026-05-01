package com.example.umc10th.domain.store.dto;

import com.example.umc10th.domain.store.enums.StoreCategory;
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
            StoreCategory category,
            String detailAddress, // WF 상에는 주소가 안 보이지만, 지도에서는 주소로 찍혀야 해서.. 애매함.. 잘 모르겠음..
            Double distance,
            String thumbnailUrl
    ) {
    }

    @Builder
    public record StoreDetailDTO(
            Long storeId,
            String storeName,
            String detailAddress,
            StoreCategory category,
            Boolean open, // 영업 중인지 아닌지
            String thumbnailUrl,
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
