package com.example.airbnb.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class Review {
    @Id
    private String id;
    private String accommodationId;
    private int rating;
    private String message;
    @DBRef
    private User user;
}
