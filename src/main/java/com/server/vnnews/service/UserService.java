package com.server.vnnews.service;

import com.server.vnnews.dto.UserDTO;
import com.server.vnnews.entity.User;
import com.server.vnnews.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long userId) {
        return  userRepository.findByUserId(userId);
    }

    public UserDTO getUserById2(Long userId) {
        return  userRepository.findByUserId2(userId);
    }
}
