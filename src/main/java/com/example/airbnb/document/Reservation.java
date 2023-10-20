package com.example.airbnb.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "reservations")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    private String id;
    private String accommodationId;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;
    private int totalPrice;

    @DBRef
    private User user;
    @DBRef
    private Accommodation accommodation;
}
