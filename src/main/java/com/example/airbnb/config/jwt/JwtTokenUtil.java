package com.example.airbnb.config.jwt;

import com.example.airbnb.config.auth.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtTokenUtil {
    private static final String SECRET_KEY = "your-secret-key";
    private static final SecretKey KEY_SPEC = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 86400000; // 토큰 만료 시간 (24시간)

    public String generateToken(UserDetailsImpl userDetails) {
        Date now = new Date();

        return Jwts.builder()
                .claim("id", userDetails.getId())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + EXPIRATION_TIME))
                .signWith(KEY_SPEC)
                .compact();
    }
}
