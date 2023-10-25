package com.example.airbnb.config.jwt;

import com.example.airbnb.config.auth.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Key;


public class JwtTokenFilter extends OncePerRequestFilter {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtTokenUtil jwtTokenUtil;

    public JwtTokenFilter(UserDetailsServiceImpl userDetailsServiceImpl, JwtTokenUtil jwtTokenUtil) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
            String jwt = jwtTokenUtil.extractJwtFromRequest(request);
            if (jwt != null && jwtTokenUtil.validateJwtToken(jwt)) {
                String username = jwtTokenUtil.getUsernameFromJwt(jwt);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }


        filterChain.doFilter(request, response);
    }
}
