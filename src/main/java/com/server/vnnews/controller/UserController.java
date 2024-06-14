package com.server.vnnews.controller;

import com.server.vnnews.dto.ArticleScrollPageDTO;
import com.server.vnnews.dto.UserInfoDTO;
import com.server.vnnews.entity.Follow;
import com.server.vnnews.entity.User;
import com.server.vnnews.entity.composite.FollowId;
import com.server.vnnews.exception.AppRuntimeException;
import com.server.vnnews.service.AuthenticationService;
import com.server.vnnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/get-user-info")
    public ResponseEntity<UserInfoDTO> getUserInfo(@RequestParam(value = "userId", required = true) Long userId,
                                                   @RequestHeader("Authorization") String token){
        String jwt = token.substring(7);
        UserInfoDTO userInfoDTO;
        try {
            Long userIdFromToken = AuthenticationService.getUserIdFromToken(jwt);
            userInfoDTO = userService.getUserInfo(userId, userIdFromToken);
//            System.out.println(userInfoDTO.getIsFollowedByLoginUser());
            System.out.println(userIdFromToken);
        } catch (ParseException e) {
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
        System.out.print("Controller getIsFollowedByLoginUser : ");
        System.out.println(userInfoDTO.getIsFollowedByLoginUser());
        return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
    }
    @GetMapping("/get-follow-followed")
    public ResponseEntity<List<FollowId>> getFollowByFollowed(){
        return new ResponseEntity<>(userService.getFollowByFollowed(), HttpStatus.OK);
    }

    @GetMapping("/get-follow-follower")
    public ResponseEntity<List<FollowId>> getFollowByFollower(){
        return new ResponseEntity<>(userService.getFollowByFollower(), HttpStatus.OK);
    }

}
