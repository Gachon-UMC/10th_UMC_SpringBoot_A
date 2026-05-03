package com.example.umc10th.domain.review.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "리뷰를 찾을 수 없습니다."),
    REVIEW_PHOTO_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_2", "리뷰 사진을 찾을 수 없습니다."),
    FORBIDDEN_REVIEW(HttpStatus.FORBIDDEN, "REVIEW403_1", "해당 리뷰에 접근할 권한이 없습니다."),
    INVALID_REVIEW_REQUEST(HttpStatus.BAD_REQUEST, "REVIEW400_1", "리뷰 요청 값이 올바르지 않습니다."),
    INVALID_REVIEW_CURSOR(HttpStatus.BAD_REQUEST, "REVIEW400_2", "커서 값이 올바르지 않습니다."),
    INVALID_STAR_RATE(HttpStatus.BAD_REQUEST, "REVIEW400_3", "별점 값이 올바르지 않습니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT, "REVIEW409_1", "이미 작성한 리뷰가 존재합니다."),
    REVIEW_ALREADY_DELETED(HttpStatus.CONFLICT, "REVIEW409_2", "이미 삭제된 리뷰입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}