package com.server.vnnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.server.vnnews.entity.Follow;
import com.server.vnnews.entity.composite.FollowId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {
    // Truy vấn ds follower đang dõi user
    @Query("SELECT new com.server.vnnews.entity.composite.FollowId(f.id.followedId, f.id.followerId) " +
            "FROM Follow f " +
            "WHERE f.id.followerId = :followerId")
    List<FollowId> findByFollowerId(@Param("followerId") Long followerId);

    // Truy vấn ds user đang dõi người khác
    @Query("SELECT new com.server.vnnews.entity.composite.FollowId(f.id.followedId, f.id.followerId) " +
            "FROM Follow f " +
            "WHERE f.id.followedId = :followedId")
    List<FollowId> findByFollowedId(@Param("followedId") Long followedId);

    // unfollow
//    @Query("DELETE FROM Follow f " +
//            "WHERE f.id.followedId = :followedId " +
//            "AND f.id.followerId = :followerId")
//    void deleteByFollowedIdAndFollowerId(@Param("followedId") Long followedId, @Param("followerId") Long followerId);
    void deleteByIdFollowedIdAndIdFollowerId(Long followedId, Long followerId);
}
