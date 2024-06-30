package com.server.vnnews.entity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", columnDefinition = "BIGINT")
    private Long notificationId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date time;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Column(name = "content")
    private String content;

    // Relation "One"

    // Relation "Many"
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "notification_type_id")
    private NotificationType notificationType;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "commment_id")
    private Comment comment;

    @Column(name = "is_seen")
    private Integer isSeen;


    // Constructors, getters, and setters


    public Integer getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(Integer isSeen) {
        this.isSeen = isSeen;
    }

    public Notification() {
    }

    public Notification(Long notificationId, Date time, String content, User user, NotificationType notificationType, Article article, Comment comment, Integer isSeen) {
        this.notificationId = notificationId;
        this.time = time;
        this.content = content;
        this.user = user;
        this.notificationType = notificationType;
        this.article = article;
        this.comment = comment;
        this.isSeen = isSeen;
    }
}
