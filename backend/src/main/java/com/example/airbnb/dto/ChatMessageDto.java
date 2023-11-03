package com.example.airbnb.dto;

import com.example.airbnb.document.ChatMessage;
import com.example.airbnb.document.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ChatMessageDto{
    private String chatRoomId;
    private ResponseUser senderId;
    private String content;

    public ChatMessageDto(ChatMessage chatMessage){
        this.chatRoomId = chatMessage.getChatRoomId();
        this.senderId = new ResponseUser(chatMessage.getSender());
        this.content = chatMessage.getContent();
    }
}
