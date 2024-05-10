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
}
