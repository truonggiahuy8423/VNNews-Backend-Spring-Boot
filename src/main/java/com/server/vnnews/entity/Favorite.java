package com.server.vnnews.entity;

import com.server.vnnews.entity.composite.FavoriteId;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "favorite")
public class Favorite {

    @EmbeddedId
    private FavoriteId id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date time;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "article_id", insertable = false, updatable = false)
    private Article article;

    // Constructors, getters, and setters
}
