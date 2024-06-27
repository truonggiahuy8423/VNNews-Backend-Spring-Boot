package com.server.vnnews.controller;

import com.server.vnnews.dto.FollowRequest;
import com.server.vnnews.entity.Follow;
import com.server.vnnews.entity.composite.FollowId;
import com.server.vnnews.exception.AppRuntimeException;
import com.server.vnnews.service.AuthenticationService;
import com.server.vnnews.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;

@RestController
public class FollowController {
    @Autowired
    private FollowService followService;

    @PostMapping("/follow-request")
    public ResponseEntity<Follow> createFollow(@RequestBody FollowRequest followRequest) {
        Long followedId = followRequest.getFollowedId();
        Long followerId = followRequest.getFollowerId();
        Date time = followRequest.getTime();
        Follow follow = followService.saveFollow(followedId, followerId, time);
        return ResponseEntity.ok(follow);
    }

    @DeleteMapping("/unfollow-request")
    public ResponseEntity<Void> deleteFollow(@RequestParam(value = "followedId", required = true) Long followedId,
                                             @RequestParam(value = "followerId", required = true) Long followerId) {
        followService.deleteFollow(followedId, followerId);
        return ResponseEntity.ok().build();
    }
}
