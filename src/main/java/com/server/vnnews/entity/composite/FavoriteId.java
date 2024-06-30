package com.server.vnnews.entity.composite;
import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class FavoriteId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "article_id")
    private Long articleId;

    // Constructors, getters, and setters

    public FavoriteId() {
    }

    public FavoriteId(Long userId, Long articleId) {
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
