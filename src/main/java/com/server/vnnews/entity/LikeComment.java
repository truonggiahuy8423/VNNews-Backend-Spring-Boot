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
    @MapsId("userId")  // Đây là quan trọng để ánh xạ chính xác composite key
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @MapsId("commentId")  // Đây là quan trọng để ánh xạ chính xác composite key
    @JoinColumn(name = "comment_id", insertable = false, updatable = false)
    private Comment comment;

    // Constructors, getters, and setters

    public LikeComment() {
    }

    public LikeComment(LikeCommentId id, Date time, User user, Comment comment) {
        this.id = id;
        this.time = time;
        this.user = user;
        this.comment = comment;
    }

    public LikeCommentId getId() {
        return id;
    }

    public void setId(LikeCommentId id) {
        this.id = id;
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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
