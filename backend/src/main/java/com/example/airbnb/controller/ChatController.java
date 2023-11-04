package com.example.airbnb.controller;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.document.ChatMessage;
import com.example.airbnb.dto.ChatMessageDto;
import com.example.airbnb.dto.ResponseChatRoom;
import com.example.airbnb.service.ChatRoomService;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.airbnb.utils.ApiUtils.ApiResult;
import static com.example.airbnb.utils.ApiUtils.success;
@Controller
@RequiredArgsConstructor
//@Api(tags = "Chat")
public class ChatController {
    private final ChatRoomService chatRoomService;
    @MessageMapping("/chat/{chatRoomId}") // 토픽을 채팅방 ID로 구분
    @SendTo("/sub/chat/{chatRoomId}") // 토픽을 통해 메시지 전달
//    @ApiImplicitParams(value = {
//             @ApiImplicitParam(name = "chatRoomId", value = "채팅방 ID", required = true, dataType = "String")
//            ,@ApiImplicitParam(name = "message", value = "메시지", required = true, dataType = "ChatMessageDto")
//    })
//    @ApiOperation (value = "채팅방 메시지 전송", notes = "채팅방 메시지 전송")
    public ChatMessageDto sendMessage(@DestinationVariable String chatRoomId, ChatMessageDto message) {
        System.out.println(message);
        chatRoomService.updateChatMessage(chatRoomId, message);
        // 메시지 처리 로직
        return message;
    }
}
