package com.server.vnnews.entity;

import com.server.vnnews.entity.composite.ArticleCategoryId;
import jakarta.persistence.*;

@Entity
@Table(name = "article_category")
public class ArticleCategory {
    @EmbeddedId
    private ArticleCategoryId id;
    // Relation "One"

    // Relation "Many"
    @ManyToOne
    @JoinColumn(name = "article_id", insertable = false, updatable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    // Constructors, getters, and setters

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
