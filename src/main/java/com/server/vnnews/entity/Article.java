package com.server.vnnews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    public List<BodyItem> getBodyItems() {
        return bodyItems;
    }

    public void setBodyItems(List<BodyItem> bodyItems) {
        this.bodyItems = bodyItems;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<ArticleCategory> getArticleCategories() {
        return articleCategories;
    }

    public void setArticleCategories(List<ArticleCategory> articleCategories) {
        this.articleCategories = articleCategories;
    }

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

    public List<SeeLater> getSeeLaters() {
        return seeLaters;
    }

    public void setSeeLaters(List<SeeLater> seeLaters) {
        this.seeLaters = seeLaters;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @Lob
    @Column(name = "thumbnail")
    private byte[] thumbnail;

    @Column(name = "thumbnail_name")
    private String thumbnailName;

    // Relation "One"
    @JsonIgnore
    @OneToMany(mappedBy = "article")
    private List<BodyItem> bodyItems;

    @JsonIgnore
    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "article")
    private List<ArticleCategory> articleCategories;

    @JsonIgnore
    @OneToMany(mappedBy = "article")
    private List<View> views;

    @JsonIgnore
    @OneToMany(mappedBy = "article")
    private List<SeeLater> seeLaters;

    @JsonIgnore
    @OneToMany(mappedBy = "article")
    private List<Favorite> favorites;

    @JsonIgnore
    @OneToMany(mappedBy = "article")
    private List<Notification> notifications;

    // Relation "Many"
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    // Constructors, getters, and setters
    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailName() {
        return thumbnailName;
    }

    public void setThumbnailName(String thumbnailName) {
        this.thumbnailName = thumbnailName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
