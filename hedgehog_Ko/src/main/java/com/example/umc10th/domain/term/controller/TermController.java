package com.example.umc10th.domain.term.controller;

import com.example.umc10th.domain.term.dto.TermResDTO;
import com.example.umc10th.domain.term.exception.code.TermSuccessCode;
import com.example.umc10th.domain.term.service.TermService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/terms")
public class TermController implements TermControllerDocs {

    private final TermService termService;

    @Override
    @GetMapping
    public ApiResponse<TermResDTO.TermListDTO> getTerms() {
        TermResDTO.TermListDTO response = termService.getTerms();
        return ApiResponse.onSuccess(TermSuccessCode.GET_TERM_LIST_SUCCESS, response);
    }

    @Override
    @GetMapping("/{termId}")
    public ApiResponse<TermResDTO.TermDetailDTO> getTermDetail(
            @PathVariable Long termId
    ) {
        TermResDTO.TermDetailDTO response = termService.getTermDetail(termId);
        return ApiResponse.onSuccess(TermSuccessCode.GET_TERM_DETAIL_SUCCESS, response);
    }
}
