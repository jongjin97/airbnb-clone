package com.example.airbnb.dto;

<<<<<<< HEAD
import com.example.airbnb.document.ChatMessage;

import lombok.*;

import java.util.List;
=======
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
>>>>>>> parent of c842053 (ChatRoom 생성, 조회, ChatMessage 저장)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChatRoom {
    // @ApiModelProperty(value = "채팅방 id", example = "1", required = true)
    private String id;
    // @ApiModelProperty(value = "채팅방 유저", required = true)
    private ResponseUser user;
    //  @ApiModelProperty(value = "채팅방 유저", required = true)
    private ResponseUser user2;
<<<<<<< HEAD
    // @ApiModelProperty(value = "채팅방 메시지", required = true)
=======
<<<<<<< HEAD
    @ApiModelProperty(value = "채팅방 메시지", required = true)
>>>>>>> ed174cfb245f5c9e6e8d74675756b43cd7102611
    private List<ChatMessageDto> messages;
=======
>>>>>>> parent of c842053 (ChatRoom 생성, 조회, ChatMessage 저장)
}
