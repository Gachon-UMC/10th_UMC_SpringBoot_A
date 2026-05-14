package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.ReviewPreViewList getUserReviewList(Long userId, Long cursorId, BigDecimal cursorStar, String sortBy, Integer size) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Pageable pageable = PageRequest.of(0, size);

        Slice<Review> reviewSlice;
        if ("star".equals(sortBy)) {
            reviewSlice = reviewRepository.findAllByUserOrderByStarDescIdDesc(user, cursorStar, cursorId, pageable);
        } else {
            reviewSlice = reviewRepository.findAllByUserOrderByIdDesc(user, cursorId, pageable);
        }

        Long totalCount = reviewRepository.countByUser(user);
        return ReviewConverter.toReviewPreViewList(reviewSlice, totalCount);
    }

    public Long getUserReviewCount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        return reviewRepository.countByUser(user);
    }

    @Transactional
    public ReviewResDTO.ReviewResult createReview(Long storeId, ReviewReqDTO.Create request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        Review review = Review.builder()
                .content(request.getContent())
                .star(request.getStar())
                .user(user)
                .store(store)
                .build();

        Review savedReview = reviewRepository.save(review);
        return ReviewConverter.toReviewResultDTO(savedReview);
    }
}
