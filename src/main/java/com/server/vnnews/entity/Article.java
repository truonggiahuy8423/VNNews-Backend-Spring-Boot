package com.server.vnnews.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id", columnDefinition = "BIGINT")
    private Long articleId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date modifyTime;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "title")
    private String title;

    // Relation "One"
    @OneToMany(mappedBy = "article")
    private List<BodyItem> bodyItems;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    @OneToMany(mappedBy = "article")
    private List<ArticleCategory> articleCategories;

    @OneToMany(mappedBy = "article")
    private List<View> views;

    @OneToMany(mappedBy = "article")
    private List<SeeLater> seeLaters;

    @OneToMany(mappedBy = "article")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "article")
    private List<Notification> notifications;

    // Relation "Many"
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    // Constructors, getters, and setters
}
