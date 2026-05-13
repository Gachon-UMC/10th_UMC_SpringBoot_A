package com.example.umc10th.domain.region.service;

import com.example.umc10th.domain.region.converter.RegionConverter;
import com.example.umc10th.domain.region.dto.RegionResDTO;
import com.example.umc10th.domain.region.entity.Region;
import com.example.umc10th.domain.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionResDTO.RegionListDTO getRegions() {
        List<Region> regions = regionRepository.findAll();
        return RegionConverter.toRegionListDTO(regions);
    }
}
