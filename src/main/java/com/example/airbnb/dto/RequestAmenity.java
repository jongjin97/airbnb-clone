package com.example.airbnb.dto;

import com.example.airbnb.document.Amenity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestAmenity {
    private Long AccommodationId;
    private List<String> view;
    private List<String> bathroom;
    private List<String> kitchen;
    private List<String> internet;
    private List<String> laundry;

    public Amenity toAmenity() {
        return Amenity.builder()
                .view(view)
                .bathroom(bathroom)
                .kitchen(kitchen)
                .internet(internet)
                .laundry(laundry)
                .build();
    }
}
