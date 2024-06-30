package com.server.vnnews.entity.composite;
import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class SeeLaterId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "article_id")
    private Long articleId;

    // Constructors, getters, and setters

    public SeeLaterId() {
    }

    public SeeLaterId(Long userId, Long articleId) {
        this.userId = userId;
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
