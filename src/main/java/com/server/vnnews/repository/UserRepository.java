package com.server.vnnews.repository;

import com.server.vnnews.dto.UserDTO;
import com.server.vnnews.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);

    User findByUserId(Long userId);

    @Query("SELECT new com.server.vnnews.dto.UserDTO(a.userId, a.name, a.avatar, COUNT(f)) " +
            "FROM User a " +
            "LEFT JOIN a.followers f " +
            "WHERE a.userId = :userId " +
            "GROUP BY a.userId, a.name, a.avatar"
            )
    UserDTO findByUserId2(@Param("userId") Long userId);


}
