package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.exception.code.StoreSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    // 식당 정보 조회: GET /api/stores/{storeId}
    @GetMapping("/{storeId}")
    public ApiResponse<StoreResDTO.StoreInfo> getStoreInfo(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long storeId
    ) {
        return ApiResponse.onSuccess(StoreSuccessCode.DETAIL_OK, null);
    }
}