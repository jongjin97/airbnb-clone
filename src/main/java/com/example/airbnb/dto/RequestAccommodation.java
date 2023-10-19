package com.example.airbnb.dto;

import com.example.airbnb.document.Accommodation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccommodation {
    private int bathroomCount;
    private String category;
    private String description;
    private String title;
    private List<String> facility;
    private int guestCount;
    private int roomCount;
    private List<List<String>> imageSrc;
    private RequestLocation location;
    private int price;
    public Accommodation toAccommodation(){
        return Accommodation.builder()
                .bathroomCount(bathroomCount)
                .category(category)
                .description(description)
                .facility(facility)
                .guestCount(guestCount)
                .title(title)
                .roomCount(roomCount)
                .imageSrc(imageSrc)
                .location(location)
                .price(price)
                .build();
    }
}
