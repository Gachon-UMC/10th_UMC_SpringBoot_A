package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.exception.code.StoreSuccessCode;
import com.example.umc10th.domain.store.service.StoreService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {
    private final StoreService storeService;

    // 특정 지역 가게 목록 조회
    @GetMapping
    public ApiResponse<StoreResDTO.StoreListDTO> getStoreList(
            @RequestParam Long regionId,
            @RequestParam Long cursor,
            @RequestParam Integer size // 이렇게 처리해도 될려나?
    ) {
        List<StoreResDTO.StorePreviewDTO> stores = List.of(
                StoreResDTO.StorePreviewDTO.builder()
                        .storeId(1L)
                        .storeName("반이학생마라탕")
                        .foodCategoryId(1L)
                        .foodCategoryName("중식당")
                        .detailAddress("서울시 성북구 안암동5가 102-60")
                        .distance(0.3)
                        .thumbnailImageUrl("https://example.com/store1.png")
                        .build(),
                StoreResDTO.StorePreviewDTO.builder()
                        .storeId(2L)
                        .storeName("김밥천국")
                        .foodCategoryId(1L)
                        .foodCategoryName("분식")
                        .detailAddress("서울시 성북구 안암동4가 100-123")
                        .distance(0.9)
                        .thumbnailImageUrl("https://example.com/store2.png")
                        .build()
        );

        StoreResDTO.StoreListDTO response = StoreResDTO.StoreListDTO.builder()
                .stores(stores)
                .nextCursor(2L)
                .hasNext(true)
                .size(stores.size())
                .build();

        return ApiResponse.onSuccess(StoreSuccessCode.GET_STORE_LIST_SUCCESS, response);
    }

    // 가게 상세 조회
    @GetMapping("/{storeId}")
    public ApiResponse<StoreResDTO.StoreDetailDTO> getStoreDetail(
            @PathVariable Long storeId
    ) {
        StoreResDTO.StoreDetailDTO response = StoreResDTO.StoreDetailDTO.builder()
                .storeId(storeId)
                .storeName("반이학생마라탕")
                .detailAddress("서울시 성북구 안암동4가 100-123")
                .foodCategoryId(1L)
                .foodCategoryName("중식당")
                .open(true)
                .thumbnailImageUrl("https://example.com/store1.png")
                .averageStar(4.5)
                .reviewCount(2)
                .photoUrls(List.of(
                        "https://example.com/store-photo1.png",
                        "https://example.com/store-photo2.png"
                ))
                .reviews(List.of(
                        StoreResDTO.ReviewPreviewDTO.builder()
                                .reviewId(1L)
                                .userName("닉네임1234")
                                .star(4.5)
                                .content("음 너무 맛있어요 포인트도 얻고 맛있는 맛집도 알게 된 것 같아 너무나도 행복한 식사였답니다. 다음에 또 올게요!!")
                                .photoUrls(List.of("https://example.com/review1.png"))
                                .build(),
                        StoreResDTO.ReviewPreviewDTO.builder()
                                .reviewId(2L)
                                .userName("닉네임1235")
                                .star(4.5)
                                .content("가격이 합리적이고 양이 적당하고 괜찮았습니다. 포인트도 덤으로 얻고 좋네요!!")
                                .photoUrls(List.of())
                                .build()
                ))
                .build();

        return ApiResponse.onSuccess(StoreSuccessCode.GET_STORE_DETAIL_SUCCESS, response);

    }
}
