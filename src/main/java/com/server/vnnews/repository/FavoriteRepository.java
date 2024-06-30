package com.server.vnnews.repository;

import com.server.vnnews.entity.Article;
import com.server.vnnews.entity.Favorite;
import com.server.vnnews.entity.composite.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    // Bạn có thể thêm các phương thức tùy chỉnh khác ở đây nếu cần


    @Query("SELECT f.article FROM Favorite f")
    List<Article> findAllFavoriteArticles();
}
