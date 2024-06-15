package com.server.vnnews.entity;
import com.server.vnnews.entity.composite.FollowId;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "follow")
public class Follow {

    @EmbeddedId
    private FollowId id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date time;

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followed_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User followed;

    // Constructors, getters, and setters

    public Follow() {}

    public Follow(FollowId id, Date time) {
        this.id = id;
        this.time = time;
    }

    public FollowId getId() {
        return id;
    }

    public void setId(FollowId id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
