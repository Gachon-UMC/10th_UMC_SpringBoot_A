package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n " +
           "WHERE n.user.id = :userId " +
           "ORDER BY n.id DESC")
    Slice<Notification> findByUserIdOrderByIdDesc(
            @Param("userId") Long userId,
            Pageable pageable
    );

    @Query("SELECT n FROM Notification n " +
           "WHERE n.user.id = :userId AND n.id < :cursor " +
           "ORDER BY n.id DESC")
    Slice<Notification> findByUserIdAndIdLessThanOrderByIdDesc(
            @Param("userId") Long userId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}
