package com.server.vnnews.dto;

import java.util.Date;

public class UserNavigationMenu {
    private Long userId;
    private String name;
    private String email;
    private byte[] avatar;

    public UserNavigationMenu(Long userId, String name, String email, byte[] avatar) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
}
