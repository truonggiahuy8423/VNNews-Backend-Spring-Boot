package com.server.vnnews.service;

import com.server.vnnews.dto.NotificationDTO;
import com.server.vnnews.entity.Notification;
import com.server.vnnews.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;


    public List<NotificationDTO> getNotificationByUserId(Long userId){
        return notificationRepository.findByUserId(userId);
    }
}
