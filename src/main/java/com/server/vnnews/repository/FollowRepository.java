package com.server.vnnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.server.vnnews.entity.Follow;
import com.server.vnnews.entity.composite.FollowId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {
    // Truy vấn danh sách các người dùng mà một người dùng cụ thể đang theo dõi
    @Query("SELECT new com.server.vnnews.entity.composite.FollowId(f.id.followerId, f.id.followedId) " +
            "FROM Follow f " +
            "WHERE f.id.followerId = :followerId")
    List<FollowId> findByFollowerId(@Param("followerId") Long followerId);

    // Truy vấn danh sách các người dùng theo dõi một người dùng cụ thể
    @Query("SELECT new com.server.vnnews.entity.composite.FollowId(f.id.followerId, f.id.followedId) " +
            "FROM Follow f " +
            "WHERE f.id.followedId = :followedId")
    List<FollowId> findByFollowedId(@Param("followedId") Long followedId);
}
