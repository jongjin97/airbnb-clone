package com.example.airbnb.repository.template;

import com.example.airbnb.document.ChatRoom;

public interface ChatRoomTemplate {

    ChatRoom findByUserIdAndUserId2(String userId, String UserId2);
}
