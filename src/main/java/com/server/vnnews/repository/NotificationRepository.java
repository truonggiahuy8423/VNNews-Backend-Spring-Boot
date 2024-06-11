package com.server.vnnews.repository;

import com.server.vnnews.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    //get by user id
    List<Notification> findByUserId(Long userId);
}
