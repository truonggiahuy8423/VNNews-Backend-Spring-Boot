package com.server.vnnews.repository;

import com.server.vnnews.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);

    User findByUserId(Long userId);
}
