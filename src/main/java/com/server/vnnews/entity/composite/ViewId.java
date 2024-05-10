package com.server.vnnews.entity.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ViewId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "article_id")
    private Long articleId;

    // Constructors, getters, and setters
}
