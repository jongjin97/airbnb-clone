package com.example.airbnb.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "reservations")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class Reservation {
    @Id
    private String id;
    private String accommodationId;
    private Date startDate;
    private Date endDate;
    private String status;
    private int totalPrice;

    @DBRef
    private User user;
    @DBRef
    private Accommodation accommodation;
}
