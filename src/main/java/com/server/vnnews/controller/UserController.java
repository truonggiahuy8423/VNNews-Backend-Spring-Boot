package com.server.vnnews.controller;

import com.server.vnnews.dto.UserDTO;
import com.server.vnnews.entity.User;
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
    @GetMapping("/api/user/get-user-info")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        Long userIdFromToken;
        try {
            userIdFromToken = AuthenticationService.getUserIdFromToken(jwt);
        } catch (ParseException e) {
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
        return new ResponseEntity<>(userService.getUserById(userIdFromToken), HttpStatus.OK);
    }

    @GetMapping(value = "/api/user/get-user-info2", produces = "application/json")
    public ResponseEntity<UserDTO> getUser2(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        Long userIdFromToken;
        try {
            userIdFromToken = AuthenticationService.getUserIdFromToken(jwt);
        } catch (ParseException e) {
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
        return new ResponseEntity<>(userService.getUserById2(userIdFromToken), HttpStatus.OK);
    }

}
