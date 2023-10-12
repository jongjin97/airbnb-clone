package com.example.airbnb.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtTokenUtil {
    private static final String SECRET_KEY = "your-secret-key";
    private static final SecretKeySpec KEY_SPEC = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS512.getJcaName());
    private static final long EXPIRATION_TIME = 86400000; // 토큰 만료 시간 (24시간)

    public static String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Date now = new Date();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + EXPIRATION_TIME))
                .signWith(KEY_SPEC)
                .compact();
    }
}
