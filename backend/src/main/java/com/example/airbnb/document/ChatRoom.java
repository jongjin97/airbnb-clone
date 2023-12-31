package com.example.airbnb.document;

import com.example.airbnb.dto.ChatMessageDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "chat_room")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class ChatRoom {
    @Id
    private String id;
    private String userId;
    private String otherUserId;

    @DBRef
    private List<ChatMessage> chatMessages;
}
