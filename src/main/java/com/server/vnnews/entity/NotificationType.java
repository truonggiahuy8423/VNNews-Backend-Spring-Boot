package com.server.vnnews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "notification_type")
public class NotificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_type_id", columnDefinition = "INT")
    private Long notificationTypeId;

    @Column(name = "type", columnDefinition = "VARCHAR(50)")
    private String type;

    public Long getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(Long notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    // Relation "One"
    @JsonIgnore
    @OneToMany(mappedBy = "notificationType")
    private List<Notification> notifications;

    // Relation "Many"

    // Constructors, getters, setters, etc.
}
