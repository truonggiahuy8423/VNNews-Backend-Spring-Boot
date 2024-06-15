package com.server.vnnews.service;

import com.server.vnnews.dto.UserInfoDTO;
import com.server.vnnews.entity.Follow;
import com.server.vnnews.entity.User;
import com.server.vnnews.entity.composite.FollowId;
import com.server.vnnews.repository.ArticleRepository;
import com.server.vnnews.repository.FollowRepository;
import com.server.vnnews.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private FollowRepository followRepository;

    public UserInfoDTO getUserInfo(Long userId, Long loginUserId){
        User userInfo = userRepository.findByUserId(userId);
        List<FollowId> followedList = followRepository.findByFollowedId(userId);
        List<FollowId> followerList = followRepository.findByFollowerId(userId);

        Long postCount = articleRepository.getNoPostByUserId(userId);
        Long followedCount = Long.parseLong(String.valueOf(followedList.size()));
        Long followingCount = Long.parseLong(String.valueOf(followerList.size()));

        boolean isFollowedByLoginUser = followedList.stream().anyMatch(follow -> follow.getFollowerId() == loginUserId);
        System.out.print("User service: ");
        System.out.println(isFollowedByLoginUser);

        UserInfoDTO userInfoDTO = new UserInfoDTO(userId,
                                                userInfo.getName(),
                                                userInfo.getRole(),
                                                postCount,
                                                followedCount,
                                                followingCount,
                                                loginUserId,
                                                isFollowedByLoginUser,
                                                userInfo.getAvatar());
        return userInfoDTO;
    }
    public User getByUserId(){
        return userRepository.findByUserId(Long.parseLong(String.valueOf("2")));
    }
    public List<FollowId> getFollowByFollowed(){
        return followRepository.findByFollowerId(Long.parseLong(String.valueOf("2")));
    }
    public List<FollowId> getFollowByFollower(){
        return followRepository.findByFollowedId(Long.parseLong(String.valueOf("2")));
    }
}
