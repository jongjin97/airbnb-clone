package com.example.airbnb.dto;

import com.example.airbnb.document.Amenity;
import com.example.airbnb.entity.Accommodation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccommodation {
    private Accommodation accommodation;
    private Amenity amenity;
}
