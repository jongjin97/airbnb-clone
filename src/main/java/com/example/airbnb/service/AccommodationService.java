package com.example.airbnb.service;

import com.example.airbnb.document.Amenity;
import com.example.airbnb.entity.Accommodation;
import com.example.airbnb.repository.AccommodationRepository;
import com.example.airbnb.repository.AmenityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AmenityRepository amenityRepository;

    public String AccommodationSave(Accommodation accommodation, Amenity amenity){
        Accommodation  savedAccommodation = accommodationRepository.save(accommodation);
        amenity.setAccommodationId(savedAccommodation.getId());
        amenityRepository.save(amenity);
        return "success";
    }
}
