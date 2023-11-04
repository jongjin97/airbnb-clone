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
//@Api(tags = "ChatRoom")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    @PostMapping("/{userId}")
//    @ApiImplicitParams(value = {
//             @ApiImplicitParam(name = "userId", value = "채팅방 생성할 유저 id", required = true, dataType = "String")
//            ,@ApiImplicitParam(name = "userDetails", value = "로그인 유저 정보", required = true, dataType = "UserDetailsImpl")
//    })
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "채팅방 생성 성공")
//            ,@ApiResponse(code = 400, message = "채팅방 생성 실패")
//    })
//    @ApiOperation(value = "채팅방 생성")
    public ApiUtils.ApiResult<String> createChatRoom(@AuthenticationPrincipal UserDetailsImpl userDetails
            , @PathVariable String userId) {
        chatRoomService.createChatRoom(userId, userDetails.getUser());

        return success("success");
    }

    @GetMapping
//    @ApiImplicitParam( name = "userDetails", value = "로그인 유저 정보", required = true, dataType = "UserDetailsImpl")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "채팅방 조회 성공")
//            ,@ApiResponse(code = 400, message = "채팅방 조회 실패")
//    })
//    @ApiOperation(value = "채팅방 조회")
    public ApiUtils.ApiResult<List<ResponseChatRoom>> getChatRooms(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<ResponseChatRoom> chatRooms = chatRoomService.findMyChatRoom(userDetails.getUser());
        return success(chatRooms);
    }
}
