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
}
