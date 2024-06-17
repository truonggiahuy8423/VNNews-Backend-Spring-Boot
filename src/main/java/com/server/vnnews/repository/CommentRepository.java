package com.server.vnnews.repository;

import com.server.vnnews.entity.Comment;
import com.server.vnnews.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh khác ở đây nếu cần
    Comment findCommentByCommentId(Long commentId);



}
