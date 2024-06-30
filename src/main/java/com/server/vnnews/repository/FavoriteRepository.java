package com.server.vnnews.repository;

import com.server.vnnews.entity.Favorite;
import com.server.vnnews.entity.composite.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    // Bạn có thể thêm các phương thức tùy chỉnh khác ở đây nếu cần

}
