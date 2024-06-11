package com.server.vnnews.dto;

import com.server.vnnews.entity.Comment;
import com.server.vnnews.entity.User;
import jakarta.persistence.*;

import java.util.Date;

public class LikeCommentDTO {

    private Date time;

    private Long userId;

    private Long commentId;

    private Boolean isSuccess;

    public LikeCommentDTO() {
    }

    public LikeCommentDTO(Date time, Long userId, Long commentId, Boolean isSuccess) {
        this.time = time;
        this.userId = userId;
        this.commentId = commentId;
        this.isSuccess = isSuccess;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }
}
