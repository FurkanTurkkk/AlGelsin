package com.AlGelsin.auth_service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${secret.key}")
    private String secretKeyString;


    public String generateToken(String username, Long authId) {
        String token = Jwts.builder()
                .setSubject(username)
                .claim("Auth-Id", authId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSecretKey())
                .compact();

        System.out.println("Generated Token: " + token);
        return token;
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }
}
