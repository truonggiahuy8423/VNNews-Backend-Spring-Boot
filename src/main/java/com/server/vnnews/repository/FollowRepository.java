package com.server.vnnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.server.vnnews.entity.Follow;
import com.server.vnnews.entity.composite.FollowId;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {
}
