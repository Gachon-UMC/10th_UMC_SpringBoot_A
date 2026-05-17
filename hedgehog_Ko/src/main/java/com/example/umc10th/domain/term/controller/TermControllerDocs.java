package com.example.umc10th.domain.term.controller;

import com.example.umc10th.domain.term.dto.TermResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Term", description = "약관 목록과 약관 상세 조회 API")
public interface TermControllerDocs {

    @Operation(
            summary = "약관 목록 조회",
            description = "회원가입과 서비스 이용에 필요한 약관 목록을 조회합니다."
    )
    ApiResponse<TermResDTO.TermListDTO> getTerms();

    @Operation(
            summary = "약관 상세 조회",
            description = "약관 ID로 특정 약관의 상세 내용을 조회합니다."
    )
    ApiResponse<TermResDTO.TermDetailDTO> getTermDetail(
            @Parameter(description = "상세 내용을 조회할 약관 ID", example = "1")
            Long termId
    );
}
