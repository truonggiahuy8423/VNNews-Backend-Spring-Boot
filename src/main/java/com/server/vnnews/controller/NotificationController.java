package com.server.vnnews.controller;

import com.server.vnnews.dto.NotificationDTO;
import com.server.vnnews.entity.Notification;
import com.server.vnnews.exception.AppRuntimeException;
import com.server.vnnews.service.AuthenticationService;
import com.server.vnnews.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    // notification by user i
    @GetMapping("/api/notification")
    public ResponseEntity<List<NotificationDTO>> getNotificationByUserId(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        try {
            Long userId = AuthenticationService.getUserIdFromToken(jwt);
            System.out.println("os: " + userId);

            List<NotificationDTO> ls = notificationService.getNotificationByUserId(userId);
            System.out.println("ls: " + ls.size());
            return new ResponseEntity<>(ls, HttpStatus.OK);
        } catch (ParseException e) {
            // handle exception
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);

        }
    }
}
