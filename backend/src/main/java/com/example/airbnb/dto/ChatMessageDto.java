package com.example.airbnb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatMessageDto {
    private String chatRoomId;
    private ResponseUser senderId;
    private String content;
}
