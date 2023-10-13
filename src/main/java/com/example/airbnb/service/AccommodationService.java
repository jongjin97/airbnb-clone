package com.example.airbnb.service;

import com.example.airbnb.document.Amenity;
import com.example.airbnb.dto.RequestAccommodationImage;
import com.example.airbnb.entity.Accommodation;
import com.example.airbnb.entity.AccommodationImage;
import com.example.airbnb.repository.AccommodationRepository;
import com.example.airbnb.repository.AmenityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AmenityRepository amenityRepository;

    @Transactional
    public void accommodationSave(Accommodation accommodation
            , Amenity amenity
            , List<RequestAccommodationImage> imageList){

        List<AccommodationImage> accommodationImages = imageList.stream()
                .map(image -> AccommodationImage.builder()
                        .imageUrl(image.getImageUrl())
                        .accommodation(accommodation)
                        .build())
                .toList();
        accommodation.setAccommodationImageList(accommodationImages);
        Accommodation  savedAccommodation = accommodationRepository.save(accommodation);

        amenity.setAccommodationId(savedAccommodation.getId());
        amenityRepository.save(amenity);
    }
}
