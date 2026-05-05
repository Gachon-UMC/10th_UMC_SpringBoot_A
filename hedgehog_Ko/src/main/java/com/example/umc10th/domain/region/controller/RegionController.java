package com.example.umc10th.domain.region.controller;

import java.util.List;

import com.example.umc10th.domain.region.dto.RegionResDTO;
import com.example.umc10th.domain.region.exception.code.RegionSuccessCode;
import com.example.umc10th.domain.region.service.RegionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/regions")
public class RegionController {

    private final RegionService regionService;

    @GetMapping
    public ApiResponse<RegionResDTO.RegionListDTO> getRegions() {
        List<RegionResDTO.RegionDTO> regions = List.of(
                RegionResDTO.RegionDTO.builder()
                        .regionId(1L)
                        .regionName("안암동")
                        .build(),
                RegionResDTO.RegionDTO.builder()
                        .regionId(2L)
                        .regionName("성수동")
                        .build(),
                RegionResDTO.RegionDTO.builder()
                        .regionId(3L)
                        .regionName("강남역")
                        .build()
        );

        RegionResDTO.RegionListDTO response = RegionResDTO.RegionListDTO.builder()
                .regions(regions)
                .build();

        return ApiResponse.onSuccess(RegionSuccessCode.GET_REGION_LIST_SUCCESS, response);
    }
}