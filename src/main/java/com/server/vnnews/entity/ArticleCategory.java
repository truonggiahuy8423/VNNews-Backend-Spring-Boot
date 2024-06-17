package com.server.vnnews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.vnnews.entity.composite.ArticleCategoryId;
import jakarta.persistence.*;

@Entity
@Table(name = "article_category")
public class ArticleCategory {
    @EmbeddedId
    private ArticleCategoryId id;
    // Relation "One"

    // Relation "Many"
    @JsonIgnore
    @ManyToOne
    @MapsId("articleId")  // Đây là quan trọng để ánh xạ chính xác composite key
    @JoinColumn(name = "article_id", insertable = false, updatable = false)
    private Article article;

    @ManyToOne
    @JsonIgnore
    @MapsId("categoryId")  // Đây là quan trọng để ánh xạ chính xác composite key
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    // Constructors, getters, and setters


    public ArticleCategory() {
    }

    public ArticleCategory(ArticleCategoryId id, Article article, Category category) {
        this.id = id;
        this.article = article;
        this.category = category;
    }

    public ArticleCategoryId getId() {
        return id;
    }

    public void setId(ArticleCategoryId id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
