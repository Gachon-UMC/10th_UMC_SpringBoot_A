package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "SELECT n FROM Notification n " +
           "WHERE n.user.id = :userId AND (:cursor IS NULL OR n.id < :cursor) " +
           "ORDER BY n.id DESC",
           countQuery = "SELECT count(n) FROM Notification n WHERE n.user.id = :userId")
    Page<Notification> findByUserIdWithCursor(@Param("userId") Long userId, @Param("cursor") Long cursor, Pageable pageable);
}
