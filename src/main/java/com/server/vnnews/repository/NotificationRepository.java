package com.server.vnnews.repository;

import com.server.vnnews.dto.NotificationDTO;
import com.server.vnnews.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    //get by user id

//    @Query("SELECT new com.server.vnnews.dto.NotificationDTO(" +
//            "n.notificationId, n.content, n.user.userId, n.notificationType.notificationTypeId, " +
//            "n.article.articleId, " +
//            "CASE WHEN n.article IS NULL THEN null ELSE n.article.thumbnail END, " +
//            "CASE WHEN n.comment IS NULL THEN null ELSE n.comment.commentId END, " +
//            "CASE WHEN n.comment IS NULL THEN '' ELSE n.comment.user.name END, " +
//            "CASE WHEN n.comment IS NULL THEN null ELSE n.comment.user.avatar END, " +
//            "n.isSeen) FROM Notification n " +
//            "WHERE n.user.userId = :userId")
//    List<NotificationDTO> findByUserId(@Param("userId") Long userId);

    @Query("SELECT new com.server.vnnews.dto.NotificationDTO(n.notificationId, n.content, n.user.userId, n.article.articleId, n.notificationType.notificationTypeId, n.isSeen, n.comment.commentId, n.article.thumbnail, n.time) FROM Notification n WHERE n.user.userId = :userId")
    List<NotificationDTO> findByUserId(@Param("userId") Long userId);
}
