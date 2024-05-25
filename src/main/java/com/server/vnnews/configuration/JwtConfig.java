package com.server.vnnews.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secretKey;

    private static String SECRET_KEY;

    @PostConstruct
    public void init() {
        System.out.println("Initializing JwtConfig with secretKey: " + secretKey);
        SECRET_KEY = secretKey;
    }

    public static String getSecretKey() {
        return SECRET_KEY;
    }

}
