package com.server.vnnews.entity.composite;
import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class LikeCommentId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "comment_id")
    private Long commentId;

    // Constructors, getters, and setters

    public LikeCommentId() {
    }

    public LikeCommentId(Long userId, Long commentId) {
        this.userId = userId;
        this.commentId = commentId;
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
}

