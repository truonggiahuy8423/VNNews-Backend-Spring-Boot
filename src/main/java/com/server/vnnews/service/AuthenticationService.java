package com.server.vnnews.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.server.vnnews.configuration.JwtConfig;
import com.server.vnnews.dto.AuthenticationRequest;
import com.server.vnnews.dto.AuthenticationResponse;
import com.server.vnnews.entity.User;
import com.server.vnnews.exception.AppRuntimeException;
import com.server.vnnews.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
public class AuthenticationService {
    @Autowired
    UserRepository userRepository;

    public AuthenticationResponse login(AuthenticationRequest request) throws JOSEException, AppRuntimeException {
        User user = userRepository.findByEmail(request.getEmail());

        if (user != null && user.getPassword().equals(request.getPassword())) {
            return new AuthenticationResponse(generateToken(user), true);
        } else {
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
    }


    public static boolean validateToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(JwtConfig.getSecretKey());
            return signedJWT.verify(verifier) && !signedJWT.getJWTClaimsSet().getExpirationTime().before(new Date());
        } catch (ParseException | JOSEException e) {
            return false;
        }
    }

    public static Long getUserIdFromToken(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return Long.valueOf(signedJWT.getJWTClaimsSet().getClaim("user_id").toString());
    }

    public static String getEmailFromToken(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getClaim("email").toString();
    }
    public static Integer getRoleIdFromToken(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return Integer.valueOf(signedJWT.getJWTClaimsSet().getClaim("role").toString());
    }
// Thay bằng secret key thực tế của bạn

    public static String generateToken(User user) throws JOSEException {
        JWSSigner signer = new MACSigner(JwtConfig.getSecretKey());

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .expirationTime(new Date(new Date().getTime() + 86400000)) // 1 day
//                .jwtID(UUID.randomUUID().toString())
                .claim("user_id", user.getUserId())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())// Thêm các claims vào payload
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }
}
