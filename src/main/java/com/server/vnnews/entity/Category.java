package com.server.vnnews.entity;

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
    @OneToMany(mappedBy = "category")
    private List<ArticleCategory> articleCategories;
    // Relation "Many"

    // Constructors, getters, and setters
}
