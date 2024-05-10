package com.server.vnnews.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "body_item")
public class BodyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "body_item_id", columnDefinition = "BIGINT")
    private Long bodyItemId;

    @Column(name = "is_thumbnail")
    private Boolean isThumbnail;

    @Column(name = "image_name",columnDefinition = "TEXT")
    private String imageName;

    @Lob
    @Column(name = "data_image")
    private byte[] dataImage;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    // Relation "One"

    // Relation "Many"
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    // Constructors, getters, and setters
}
