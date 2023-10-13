package com.example.airbnb.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collation = "amenity")
@Setter
@Getter
public class Amenity {
    @Id
    private String id;
    private Long AccommodationId;
    private List<String> view;
    private List<String> bathroom;
    private List<String> kitchen;
    private List<String> internet;
    private List<String> laundry;

}
