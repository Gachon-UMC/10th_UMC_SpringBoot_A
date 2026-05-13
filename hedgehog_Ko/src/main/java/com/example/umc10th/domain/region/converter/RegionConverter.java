package com.example.umc10th.domain.region.converter;

import com.example.umc10th.domain.region.dto.RegionResDTO;
import com.example.umc10th.domain.region.entity.Region;

import java.util.List;

public class RegionConverter {

    public static RegionResDTO.RegionDTO toRegionDTO(Region region) {
        return RegionResDTO.RegionDTO.builder()
                .regionId(region.getId())
                .regionName(region.getName())
                .build();
    }

    public static RegionResDTO.RegionListDTO toRegionListDTO(List<Region> regions) {
        return RegionResDTO.RegionListDTO.builder()
                .regions(regions.stream()
                        .map(RegionConverter::toRegionDTO)
                        .toList())
                .build();
    }
}
