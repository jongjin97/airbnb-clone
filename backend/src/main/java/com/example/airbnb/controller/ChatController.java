package com.example.airbnb.controller;

import com.example.airbnb.document.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat/{chatRoomId}") // 토픽을 채팅방 ID로 구분
    @SendTo("/topic/chat/{chatRoomId}") // 토픽을 통해 메시지 전달
    public ChatMessage sendMessage(@DestinationVariable String chatRoomId, ChatMessage message) {
        // 메시지 처리 로직
        return message;
    }
}
