package com.server.vnnews.repository;

import com.server.vnnews.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh khác ở đây nếu cần
}
