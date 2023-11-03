package com.example.airbnb.dto;

<<<<<<< HEAD
import com.example.airbnb.document.ChatMessage;
import com.example.airbnb.document.User;
import io.swagger.annotations.ApiModelProperty;
=======
>>>>>>> parent of c842053 (ChatRoom 생성, 조회, ChatMessage 저장)
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
<<<<<<< HEAD
@Builder
public class ChatMessageDto{
    @ApiModelProperty (value = "채팅방 ID")
=======
public class ChatMessageDto {
>>>>>>> parent of c842053 (ChatRoom 생성, 조회, ChatMessage 저장)
    private String chatRoomId;
    @ApiModelProperty (value = "메시지 보낸 사람 ID")
    private ResponseUser senderId;
    @ApiModelProperty (value = "메시지 내용")
    private String content;
}
