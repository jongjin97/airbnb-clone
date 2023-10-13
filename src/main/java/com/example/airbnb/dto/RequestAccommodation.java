package com.example.airbnb.dto;

import com.example.airbnb.document.Amenity;
import com.example.airbnb.entity.Accommodation;
import com.example.airbnb.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccommodation {
    private String name; // 이름
    private String description; // 설명
    private String address; // 주소
    private String city; // 도시
    private String country; // 지역
    private String type;  // 공간전체, 방, 다인실
    private String price; // 가격
    private String status; // N, Y
    private String category; // 카테고리
    private RequestAmenity amenity;
    private List<RequestRoom> room;

    public Accommodation toEntity() {
        return Accommodation.builder()
                .name(name)
                .description(description)
                .address(address)
                .city(city)
                .country(country)
                .type(type)
                .price(price)
                .status(status)
                .category(category)
                .build();
    }
}
