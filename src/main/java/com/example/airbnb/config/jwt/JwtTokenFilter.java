package com.example.airbnb.config.jwt;

import com.example.airbnb.config.auth.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;



public class JwtTokenFilter extends OncePerRequestFilter {
    private static final String SECRET_KEY = "your-secret-key";
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public JwtTokenFilter(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
            String jwt = extractJwtFromRequest(request);
            if (jwt != null && validateJwtToken(jwt)) {
                String username = getUsernameFromJwt(jwt);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }


        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        // 헤더 또는 쿠키에서 JWT 토큰 추출하는 로직 구현
        // 예: Authorization 헤더에서 "Bearer <token>" 형식으로 추출

        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "Bearer " 이후의 부분 반환
        }

        return null; // JWT 토큰이 없을 경우에는 null 반환
    }

    private boolean validateJwtToken(String jwt) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwt);
            return true; // 유효한 JWT 토큰인 경우 true 반환
        } catch (Exception e) {
            return false; // 유효하지 않은 JWT 토큰인 경우 false 반환
        }
    }

    private String getUsernameFromJwt(String jwt) {
        Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwt).getBody();
        return claims.getSubject();
    }
}
