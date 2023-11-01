package com.example.airbnb.controller;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.dto.ResponseChatRoom;
import com.example.airbnb.service.ChatRoomService;
import com.example.airbnb.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.airbnb.utils.ApiUtils.success;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatroom")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    @PostMapping("/{userId}")
    public ApiUtils.ApiResult<String> createChatRoom(@AuthenticationPrincipal UserDetailsImpl userDetails
            , @PathVariable String userId) {
        chatRoomService.createChatRoom(userId, userDetails.getUser());

        return success("success");
    }

    @GetMapping
    public ApiUtils.ApiResult<List<ResponseChatRoom>> getChatRooms(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<ResponseChatRoom> chatRooms = chatRoomService.findMyChatRoom(userDetails.getUser());
        return success(chatRooms);
    }
}
