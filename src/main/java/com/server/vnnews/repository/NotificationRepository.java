package com.server.vnnews.repository;

import com.server.vnnews.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    //get by user id

    @Query("SELECT n FROM Notification n WHERE n.user.userId = ?1")
    List<Notification> findByUserId(Long userId);
}
