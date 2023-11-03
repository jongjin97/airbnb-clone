package com.example.airbnb.dto;

import com.example.airbnb.document.ChatMessage;
import com.example.airbnb.document.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ChatMessageDto{
    @ApiModelProperty (value = "채팅방 ID")
    private String chatRoomId;
    @ApiModelProperty (value = "메시지 보낸 사람 ID")
    private ResponseUser senderId;
    @ApiModelProperty (value = "메시지 내용")
    private String content;

    public ChatMessageDto(ChatMessage chatMessage){
        this.chatRoomId = chatMessage.getChatRoomId();
        this.senderId = new ResponseUser(chatMessage.getSender());
        this.content = chatMessage.getContent();
    }
}
