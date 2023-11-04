package com.example.airbnb.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chatmessage")
@Data
public class ChatMessage {
    @Id
    private String id;
    private String chatRoomId;
    private String senderId;
    private String content;
}
