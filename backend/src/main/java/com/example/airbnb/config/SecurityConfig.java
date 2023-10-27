package com.example.airbnb.config;

import com.example.airbnb.config.auth.UserDetailsServiceImpl;
import com.example.airbnb.config.jwt.JwtTokenFilter;
import com.example.airbnb.config.jwt.JwtTokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtTokenUtil jwtTokenUtil;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*") // 안에 해당 주소를 넣어도 됨
                        .allowedHeaders("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS" , "PATCH")
                        .exposedHeaders("Authorization", "RefreshToken");
                //.allowCredentials(true); // .allowedOriginPatterns("*") 이렇게 와일드 카드로 설정하면 이거 쓰면 에러남 ( 실행 조차  X )
            }
        };
    }

//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//        log.warn("accessDeniedHandler");
//        return (request, response, e) -> {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.setContentType("text/plain;charset=UTF-8");
//            response.getWriter().write("ACCESS DENIED");
//            response.getWriter().flush();
//            response.getWriter().close();
//        };
//    }
//
//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        return (request, response, e) -> {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("text/plain;charset=UTF-8");
//            response.getWriter().write("UNAUTHORIZED");
//            response.getWriter().flush();
//            response.getWriter().close();
//        };
//    }
    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.POST,"/api/user/signin", "/api/user/signup").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/accommodation/*").permitAll()
                        .anyRequest().hasAuthority("USER"))
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
//                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
//                        httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(accessDeniedHandler()))
//                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
//                        httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(authenticationEntryPoint()))
                .userDetailsService(userDetailsServiceImpl)
                .addFilterBefore(new JwtTokenFilter(userDetailsServiceImpl, jwtTokenUtil), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
