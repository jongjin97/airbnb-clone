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
    // @ApiModelProperty(value = "채팅방 id", example = "1", required = true)
    private String id;
    // @ApiModelProperty(value = "채팅방 유저", required = true)
    private ResponseUser user;
    //  @ApiModelProperty(value = "채팅방 유저", required = true)
    private ResponseUser user2;
    // @ApiModelProperty(value = "채팅방 메시지", required = true)
    private List<ChatMessageDto> messages;
}
