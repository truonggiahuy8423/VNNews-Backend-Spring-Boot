package com.server.vnnews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", columnDefinition = "BIGINT")
    private Long categoryId;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;

    // Relation "One"
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<ArticleCategory> articleCategories;
    // Relation "Many"

    // Constructors, getters, and setters

    public Category() {
    }

    public Category(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArticleCategory> getArticleCategories() {
        return articleCategories;
    }

    public void setArticleCategories(List<ArticleCategory> articleCategories) {
        this.articleCategories = articleCategories;
    }
}
