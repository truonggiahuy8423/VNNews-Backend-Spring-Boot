package com.server.vnnews.repository;

import com.server.vnnews.entity.BodyItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyItemRepository extends JpaRepository<BodyItem, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh khác ở đây nếu cần
}
