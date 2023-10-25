package com.example.airbnb.document;

import com.example.airbnb.dto.RequestLocation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "accommodation")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class Accommodation {
    @Id
    private String id;
    private int bathroomCount;
    private String category;
    private String title;
    private String description;
    private List<String> facility;
    private int guestCount;
    private int roomCount;
    private List<List<String>> imageSrc;
    private RequestLocation location;
    private int price;

    @DBRef
    private User user;
    @DBRef
    private List<Reservation> reservations;
}
