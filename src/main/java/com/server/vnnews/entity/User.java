package com.server.vnnews.entity;

import jakarta.persistence.*;
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
    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "password")
    private String password;

    @Column(name = "is_author")
    private Boolean isAuthor;

    // Relation "One"
    @OneToMany(mappedBy = "user")
    private List<Article> articles;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user")
    private List<LikeComment> likeComments;

    @OneToMany(mappedBy = "user")
    private List<View> views;

    @OneToMany(mappedBy = "user")
    private List<SeeLater> seeLaters;

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "follower")
    private List<Follow> followedUsers;

    @OneToMany(mappedBy = "followed")
    private List<Follow> followers;

    // Relation "Many"

    // Constructors, getters, and setters

}
