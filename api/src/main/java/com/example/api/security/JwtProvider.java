package com.example.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import com.example.api.model.User;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

    // Keys.secretKeyFor で 256bit 以上の強度を満たす鍵が生成される
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String issueToken(User user) {
        return Jwts.builder()
                   .setSubject(user.username())
                   .setIssuedAt(new Date())
                   .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                   .signWith(SECRET_KEY)
                   .compact();
    }
}