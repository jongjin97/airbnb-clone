package com.example.airbnb.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chatmessage")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ChatMessage {
    @Id
    private String id;
    private String chatRoomId;
    private User sender;
    private String content;

}
