package com.server.vnnews.dto;


import java.util.Date;

public class NotificationDTO {

    private Long notificationId;
    private String content;
    private Long userId;
    private Long notificationTypeId;

    private Long articleId;
    private byte[] thumbnail;


    private Date time;
    private Long commentId;


    private String actorName;
    private byte[] actorAva;


    private Integer isSeen;


    public Integer getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(Integer isSeen) {
        this.isSeen = isSeen;
    }

    public NotificationDTO() {
    }

    public NotificationDTO(Long notificationId, String content, Long userId, Long notificationTypeId, Long articleId, byte[] thumbnail, Long commentId, String actorName, byte[] actorAva, Integer isSeen) {
        this.notificationId = notificationId;
        this.content = content;
        this.userId = userId;
        this.notificationTypeId = notificationTypeId;
        this.articleId = articleId;
        this.thumbnail = thumbnail;
        this.commentId = commentId;
        this.actorName = actorName;
        this.actorAva = actorAva;
        this.isSeen = isSeen;
    }

    public NotificationDTO(Long notificationId, String content, Long userId, Long articleId,  Long notificationTypeId, Integer isSeen, Long commentId, byte[] thumbnail, Date time) {
        this.notificationId = notificationId;
        this.content = content;
        this.thumbnail = thumbnail;
        this.userId = userId;
        this.articleId = articleId;
        this.notificationTypeId = notificationTypeId;
        this.isSeen = isSeen;
        this.commentId = commentId;
        this.time = time;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public byte[] getActorAva() {
        return actorAva;
    }

    public void setActorAva(byte[] actorAva) {
        this.actorAva = actorAva;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(Long notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }


}
