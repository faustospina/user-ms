package com.bci.cl.user_ms.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "your_super_long_secret_key_that_is_at_least_32_bytes!";

    private final String STATIC_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdGF0aWNVc2VyIiwiaWF0IjoxNjk2MTk0NDAwfQ.0ErH9fOpzQojisfSoszSDzEAw-j4jLVN5F4MGEZh_oI";

    public String getStaticToken() {
        return STATIC_TOKEN;
    }

    public boolean validateStaticToken(String token) {
        return STATIC_TOKEN.equals(token);
    }

    public String generateStaticToken() {
        return Jwts.builder()
                .setSubject("staticUser")
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusYears(1).toInstant(ZoneOffset.UTC)))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
