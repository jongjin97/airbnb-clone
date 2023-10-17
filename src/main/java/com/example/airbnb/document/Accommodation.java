package com.example.airbnb.document;

import com.example.airbnb.dto.RequestLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "accommodation")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Accommodation {
    @Id
    private String id;
    private int bathroomCount;
    private String category;
    private String description;
    private List<String> facility;
    private int guestCount;
    private int roomCount;
    private List<List<String>> imageSrc;
    private RequestLocation location;
}
