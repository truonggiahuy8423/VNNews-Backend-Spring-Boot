package com.server.vnnews.entity;
import jakarta.persistence.*;
import com.server.vnnews.entity.composite.LikeCommentId;
import java.util.Date;

@Entity
@Table(name = "like_comment")
public class LikeComment {

    @EmbeddedId
    private LikeCommentId id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date time;

    // Relation "Many"
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id", insertable = false, updatable = false)
    private Comment comment;

    // Constructors, getters, and setters
}
