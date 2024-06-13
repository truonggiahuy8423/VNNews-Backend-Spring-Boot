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
        List<Notification> notifications = notificationRepository.findByUserId(userId);
        List<NotificationDTO> res = new ArrayList<>();
        
        notifications.forEach(notification -> {
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setNotificationId(notification.getNotificationId());
            notificationDTO.setContent(notification.getContent());
            notificationDTO.setUserId(notification.getUser().getUserId());
            notificationDTO.setNotificationTypeId(notification.getNotificationType().getNotificationTypeId());
            notificationDTO.setArticleId(notification.getArticle().getArticleId());
            notificationDTO.setCommentId(notification.getComment().getCommentId());
            res.add(notificationDTO);
        });
        return res;
    }
}
