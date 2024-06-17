package com.server.vnnews.dto;

public class UserDTO {
    private Long userId;
    private String name;
    private byte[] avatar;
    private Long followerCount;

    public UserDTO() {
    }

    public UserDTO(Long userId, String name, byte[] avatar, Long followerCount) {
        this.userId = userId;
        this.name = name;
        this.avatar = avatar;
        this.followerCount = followerCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Long getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Long followerCount) {
        this.followerCount = followerCount;
    }
}
