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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

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
