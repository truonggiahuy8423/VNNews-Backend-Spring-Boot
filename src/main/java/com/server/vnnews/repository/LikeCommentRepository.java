package com.server.vnnews.repository;

import com.server.vnnews.entity.LikeComment;
import com.server.vnnews.entity.composite.LikeCommentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeCommentRepository extends JpaRepository<LikeComment, LikeCommentId> {
}

