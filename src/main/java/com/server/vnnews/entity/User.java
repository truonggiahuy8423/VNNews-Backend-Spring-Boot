package com.server.vnnews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", columnDefinition = "BIGINT")
    private Long userId;

    @Column(name = "register_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    @Column(name = "name", columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name = "phone", columnDefinition = "VARCHAR(10)")
    private String phone;

    @Column(name = "email", columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(name = "birth_date", columnDefinition = "DATE DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "gender")
    private Boolean gender;

    @Lob
    @JsonIgnore
    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Integer role;

    // Relation "One"
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Article> articles;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<LikeComment> likeComments;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<View> views;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<SeeLater> seeLaters;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;

    @JsonIgnore
    @OneToMany(mappedBy = "follower")
    private List<Follow> followedUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "followed")
    private List<Follow> followers;

    // Relation "Many"

    // Constructors, getters, and setters


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer author) {
        role = author;
    }
}
