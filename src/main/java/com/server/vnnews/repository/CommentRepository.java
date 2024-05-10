package com.server.vnnews.repository;

import com.server.vnnews.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh khác ở đây nếu cần
}
