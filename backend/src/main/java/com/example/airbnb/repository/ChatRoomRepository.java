package com.example.airbnb.repository;

import com.example.airbnb.document.ChatRoom;
import com.example.airbnb.repository.template.ChatRoomTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String>, ChatRoomTemplate {

    List<ChatRoom> findByUserIdOrOtherUserId(String userId, String userId2);

}