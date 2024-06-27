package com.server.vnnews.dto;

import java.util.Date;

public class FollowRequest {
    private Long followedId;
    private Long followerId;

    private Date time;

    public FollowRequest(Long followedId, Long followerId, Date time) {
        this.followerId = followerId;
        this.followedId = followedId;
        this.time = time;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
