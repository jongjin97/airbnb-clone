package com.example.airbnb.dto;

import com.example.airbnb.document.ChatMessage;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseChatRoom {
    private String id;
    private ResponseUser user;
    private ResponseUser user2;
    private List<ChatMessageDto> messages;
}
