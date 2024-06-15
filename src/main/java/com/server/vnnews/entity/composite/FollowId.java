package com.server.vnnews.entity.composite;
import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class FollowId implements Serializable {

    @Column(name = "follower_id")
    private Long followerId;

    @Column(name = "followed_id")
    private Long followedId;

    // Constructors, getters, and setters

    public FollowId() {
    }

    public FollowId(Long followedId, Long followerId) {
        this.followedId = followedId;
        this.followerId = followerId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Long followedId) {
        this.followedId = followedId;
    }
}
