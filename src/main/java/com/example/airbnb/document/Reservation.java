package com.example.airbnb.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reservation")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    private String id;
    private String accommodationId;
    private String checkInDate;
    private String checkOutDate;
    private String status;
    private int totalPrice;

    @DBRef
    private User user;
    @DBRef
    private Accommodation accommodation;
}
