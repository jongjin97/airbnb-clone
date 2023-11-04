package com.example.airbnb.service;

import com.example.airbnb.document.ChatMessage;
import com.example.airbnb.document.ChatRoom;
import com.example.airbnb.document.User;
import com.example.airbnb.dto.ChatMessageDto;
import com.example.airbnb.dto.ResponseChatRoom;
import com.example.airbnb.dto.ResponseUser;
import com.example.airbnb.repository.ChatMessageRepository;
import com.example.airbnb.repository.ChatRoomRepository;
import com.example.airbnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final ChatMessageRepository chatMessageRepository;
    public ChatRoom createChatRoom(String userId, User user) {
        ChatRoom chatRoom = chatRoomRepository.findByUserIdAndUserId2(user.getId(), userId);

        if(chatRoom == null){
            chatRoom = ChatRoom.builder().userId(userId).otherUserId(user.getId()).chatMessages(new ArrayList<>()).build();
            chatRoomRepository.save(chatRoom);
        }

        return chatRoom;
    }

    public List<ResponseChatRoom> findMyChatRoom(User user){
        List<ChatRoom> chatRooms = chatRoomRepository.findByUserIdOrOtherUserId(user.getId(), user.getId());
        List<ResponseChatRoom> responseChatRooms = new ArrayList<>();
        for(ChatRoom chatRoom: chatRooms){
            User user1 = userRepository.findById(chatRoom.getUserId()).orElse(null);
            User user2 = userRepository.findById(chatRoom.getOtherUserId()).orElse(null);
            responseChatRooms.add(ResponseChatRoom.builder()
                    .id(chatRoom.getId())
                    .user(new ResponseUser(user1))
                    .user2(new ResponseUser(user2))
                    .messages(chatRoom.getChatMessages().stream().map(ChatMessageDto::new).toList())
                    .build());
        }
        return responseChatRooms;
    }

    public void updateChatMessage(String chatRoomId, ChatMessageDto chatMessageDto){
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).get();
        User sender = userRepository.findById(chatMessageDto.getSenderId().getId()).get();
        if(chatRoom.getChatMessages() == null)
            chatRoom.setChatMessages(new ArrayList<>());
        ChatMessage chatMessage = ChatMessage.builder().chatRoomId(chatMessageDto.getChatRoomId())
                .sender(sender)
                .content(chatMessageDto.getContent()).build();
        chatMessage = chatMessageRepository.save(chatMessage);
        chatRoom.getChatMessages().add(chatMessage);
        chatRoomRepository.save(chatRoom);
    }
}
