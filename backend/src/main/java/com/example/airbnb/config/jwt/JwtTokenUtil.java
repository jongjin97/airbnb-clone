package com.example.airbnb.config.jwt;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.document.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final String SECRET_KEY = "abcdefghijklmnopqrstuwxyz0123456789qweqweqwe";

    private static final long EXPIRATION_TIME = 86400000; // 토큰 만료 시간 (24시간)

    public String generateToken(UserDetailsImpl userDetails) {
        Date now = new Date();
        SecretKey secretKey = generalKey();
        return Jwts.builder()
                .claim("id", userDetails.getId())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + EXPIRATION_TIME))
                .signWith(secretKey,  SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateTokenByUser(User user) {
        Date now = new Date();
        SecretKey secretKey = generalKey();
        return Jwts.builder()
                .claim("id", user.getId())
                .setSubject(user.getEmail())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + EXPIRATION_TIME))
                .signWith(secretKey,  SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractJwtFromRequest(HttpServletRequest request) {
        // 헤더 또는 쿠키에서 JWT 토큰 추출하는 로직 구현
        // 예: Authorization 헤더에서 "Bearer <token>" 형식으로 추출

        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "Bearer " 이후의 부분 반환
        }

        return null; // JWT 토큰이 없을 경우에는 null 반환
    }

    public boolean validateJwtToken(String jwt) {
        SecretKey secretKey = generalKey();
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt);
            return true; // 유효한 JWT 토큰인 경우 true 반환
        } catch (Exception e) {
            return false; // 유효하지 않은 JWT 토큰인 경우 false 반환
        }
    }

    public String getUsernameFromJwt(String jwt) {
        SecretKey secretKey = generalKey();
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
        return claims.getSubject();
    }
    public static SecretKey generalKey(){
        byte[] encodeKey = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(encodeKey);
    }
}
