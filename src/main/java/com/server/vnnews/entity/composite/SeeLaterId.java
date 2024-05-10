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
}
