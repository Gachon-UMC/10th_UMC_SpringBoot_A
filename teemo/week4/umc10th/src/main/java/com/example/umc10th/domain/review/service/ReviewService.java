package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.user.entity.User;
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

    public Slice<Review> getUserReviewList(Long userId, Long cursorId, BigDecimal cursorStar, String sortBy, Integer size) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Pageable pageable = PageRequest.of(0, size);

        if ("star".equals(sortBy)) {
            return reviewRepository.findAllByUserOrderByStarDescIdDesc(user, cursorStar, cursorId, pageable);
        } else {
            return reviewRepository.findAllByUserOrderByIdDesc(user, cursorId, pageable);
        }
    }

    public Long getUserReviewCount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        return reviewRepository.countByUser(user);
    }

    @Transactional
    public Review createReview(Long storeId, ReviewReqDTO.ReviewCreateDTO request) {
        Review review = ReviewConverter.toReview(request);

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        // 연관관계 편의 메서드가 없으므로 직접 빌더 등을 통해 설정하거나
        // 엔티티에 메서드를 추가하는 것이 좋지만, 일단 직접 매핑
        Review savedReview = Review.builder()
                .content(request.getContent())
                .star(request.getStar())
                .user(user)
                .store(store)
                .build();

        return reviewRepository.save(savedReview);
    }
}
