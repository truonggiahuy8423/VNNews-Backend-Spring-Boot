package com.server.vnnews.entity;

import com.server.vnnews.entity.composite.ViewId;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "view")
public class View implements Serializable {
    @EmbeddedId
    private ViewId id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time", nullable = false)
    private Date time;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @MapsId("userId")  // Đây là quan trọng để ánh xạ chính xác composite key
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "article_id", insertable = false, updatable = false)
    @MapsId("articleId")  // Đây là quan trọng để ánh xạ chính xác composite key
    private Article article;

    // Constructors, getters, and setters

    public View() {
    }

    public View(ViewId id, Date time, User user, Article article) {
        this.id = id;
        this.time = time;
        this.user = user;
        this.article = article;
    }

    public ViewId getId() {
        return id;
    }

    public void setId(ViewId id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
