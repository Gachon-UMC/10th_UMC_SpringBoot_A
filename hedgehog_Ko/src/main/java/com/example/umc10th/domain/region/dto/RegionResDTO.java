package com.example.umc10th.domain.region.dto;

import java.util.List;

import lombok.Builder;

public class RegionResDTO {

    @Builder
    public record RegionListDTO(
            List<RegionDTO> regions
    ) {
    }

    @Builder
    public record RegionDTO(
            Long regionId,
            String regionName
    ) {
    }
}