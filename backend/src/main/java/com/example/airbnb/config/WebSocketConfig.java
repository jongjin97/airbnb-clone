package com.example.airbnb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 브로커 구성
        config.enableSimpleBroker("/sub"); // 메시지를 /topic 주제로 전송
        config.setApplicationDestinationPrefixes("/pub"); // 클라이언트에서 서버로 메시지를 보낼 때 /app 프리픽스 사용
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // STOMP 엔드포인트 설정
        registry.addEndpoint("/chat").setAllowedOrigins("*"); // /chat 엔드포인트에 SockJS를 사용해 연결
    }
}