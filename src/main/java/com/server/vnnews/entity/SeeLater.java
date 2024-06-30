package com.server.vnnews.entity;
import com.server.vnnews.entity.composite.SeeLaterId;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "see_later")
public class SeeLater {

    @EmbeddedId
    private SeeLaterId id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date time;

    @ManyToOne
    @MapsId("userId")  // Đây là quan trọng để ánh xạ chính xác composite key
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @MapsId("articleId")  // Đây là quan trọng để ánh xạ chính xác composite key
    @JoinColumn(name = "article_id", referencedColumnName = "article_id", insertable = false, updatable = false)
    private Article article;

    // Constructors, getters, and setters

    public SeeLater() {
    }

    public SeeLater(SeeLaterId id, Date time, User user, Article article) {
        this.id = id;
        this.time = time;
        this.user = user;
        this.article = article;
    }

    public SeeLaterId getId() {
        return id;
    }

    public void setId(SeeLaterId id) {
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
