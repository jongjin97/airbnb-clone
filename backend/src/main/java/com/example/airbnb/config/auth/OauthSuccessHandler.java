package com.example.airbnb.config.auth;

import com.example.airbnb.config.jwt.JwtTokenUtil;
import com.example.airbnb.document.User;
import com.example.airbnb.dto.ResponseUser;
import com.example.airbnb.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class OauthSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User user2 = (OAuth2User) authentication.getPrincipal();
        authentication.getPrincipal();
        User user = userRepository.findByEmail(user2.getAttribute("email")).get();
        ResponseUser responseUser = new ResponseUser(user);

        String accessToken = jwtTokenUtil.generateTokenByUser(user);
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(responseUser);
        String encodedUserJson = URLEncoder.encode(userJson, StandardCharsets.UTF_8);
        response.sendRedirect(UriComponentsBuilder.fromUriString("http://localhost:3000/auth/login")
                .queryParam("accessToken", accessToken)
                .queryParam("user", encodedUserJson)
                .toUriString());
    }
}
