package com.example.umc10th.domain.review.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    GET_STORE_REVIEW_LIST_SUCCESS(HttpStatus.OK, "REVIEW200_1", "가게 리뷰 목록 조회에 성공했습니다."),
    CREATE_REVIEW_SUCCESS(HttpStatus.CREATED, "REVIEW201_1", "리뷰 작성에 성공했습니다."),
    GET_MY_REVIEW_LIST_SUCCESS(HttpStatus.OK, "REVIEW200_2", "내 리뷰 목록 조회에 성공했습니다."),
    UPDATE_REVIEW_SUCCESS(HttpStatus.OK, "REVIEW200_3", "리뷰 수정에 성공했습니다."),
    DELETE_REVIEW_SUCCESS(HttpStatus.OK, "REVIEW200_4", "리뷰 삭제에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}