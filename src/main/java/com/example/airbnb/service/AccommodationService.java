package com.example.airbnb.service;

import com.example.airbnb.document.Accommodation;
import com.example.airbnb.dto.RequestAccommodation;
import com.example.airbnb.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;

    public void createAccommodation(RequestAccommodation requestAccommodation) {
        Accommodation accommodation = requestAccommodation.toAccommodation();
        accommodationRepository.save(accommodation);
    }
}
