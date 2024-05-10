package com.server.vnnews.entity.composite;

import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class ArticleCategoryId implements Serializable {
    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "category_id")
    private Long categoryId;

    // Constructors, getters, and setters
}
