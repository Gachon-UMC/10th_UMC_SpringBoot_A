package com.example.umc10th.domain.term.controller;

import com.example.umc10th.domain.term.dto.TermResDTO;
import com.example.umc10th.domain.term.enums.TermType;
import com.example.umc10th.domain.term.exception.code.TermSuccessCode;
import com.example.umc10th.domain.term.service.TermService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/terms")
public class TermController {

    private final TermService termService;

    @GetMapping
    public ApiResponse<TermResDTO.TermListDTO> getTerms() {
        List<TermResDTO.TermPreviewDTO> terms = List.of(
                TermResDTO.TermPreviewDTO.builder()
                        .termId(1L)
                        .termType(TermType.SERVICE)
                        .termName("서비스 이용약관")
                        .required(true)
                        .build(),
                TermResDTO.TermPreviewDTO.builder()
                        .termId(2L)
                        .termType(TermType.PRIVACY)
                        .termName("개인정보 처리방침")
                        .required(true)
                        .build(),
                TermResDTO.TermPreviewDTO.builder()
                        .termId(3L)
                        .termType(TermType.MARKETING)
                        .termName("마케팅 정보 수신 동의")
                        .required(false)
                        .build()
        );

        TermResDTO.TermListDTO response = TermResDTO.TermListDTO.builder()
                .terms(terms)
                .build();

        return ApiResponse.onSuccess(TermSuccessCode.GET_TERM_LIST_SUCCESS, response);
    }

    @GetMapping("/{termId}")
    public ApiResponse<TermResDTO.TermDetailDTO> getTermDetail(
            @PathVariable Long termId
    ) {
        TermResDTO.TermDetailDTO response = TermResDTO.TermDetailDTO.builder()
                .termId(termId)
                .termType(TermType.SERVICE)
                .termName("서비스 이용약관")
                .required(true)
                .termContent("서비스 이용약관 본문입니다.")
                .build();

        return ApiResponse.onSuccess(TermSuccessCode.GET_TERM_DETAIL_SUCCESS, response);
    }
}
