package com.example.airbnb.repository.template;

import com.example.airbnb.document.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomTemplateImpl implements ChatRoomTemplate{
    private final MongoTemplate mongoTemplate;
    @Override
    public ChatRoom findByUserIdAndUserId2(String userId, String otherUserId) {
        Query query = new Query();
        query.addCriteria(
                new Criteria().orOperator(
                        Criteria.where("userId").is(userId).and("otherUserId").is(otherUserId),
                        Criteria.where("userId").is(otherUserId).and("otherUserId").is(userId)
                )
        );
        return mongoTemplate.findOne(query, ChatRoom.class);
    }
}
