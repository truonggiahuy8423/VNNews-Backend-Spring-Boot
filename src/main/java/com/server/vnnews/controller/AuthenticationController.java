package com.server.vnnews.controller;

import com.nimbusds.jose.JOSEException;
import com.server.vnnews.dto.AuthenticationRequest;
import com.server.vnnews.dto.AuthenticationResponse;
import com.server.vnnews.dto.NewsFeedArticleDTO;
import com.server.vnnews.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest user) throws JOSEException {
        return new ResponseEntity<>(authenticationService.login(user), HttpStatus.OK);
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest user) throws JOSEException {
        return new ResponseEntity<>(authenticationService.register(user), HttpStatus.OK);
    }

}
