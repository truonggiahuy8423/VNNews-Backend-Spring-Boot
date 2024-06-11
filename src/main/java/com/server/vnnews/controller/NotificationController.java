package com.server.vnnews.controller;

import com.server.vnnews.dto.NotificationDTO;
import com.server.vnnews.entity.Notification;
import com.server.vnnews.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    // notification by user i
    @GetMapping("/notification/{userId}")
    public List<NotificationDTO> getNotificationByUserId(@PathVariable Long userId){
        return  notificationService.getNotificationByUserId(userId);
    }
}
