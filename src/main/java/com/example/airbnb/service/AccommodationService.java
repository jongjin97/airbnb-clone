package com.example.airbnb.service;

import com.example.airbnb.document.Amenity;
import com.example.airbnb.dto.RequestAccommodation;
import com.example.airbnb.dto.RequestAccommodationImage;
import com.example.airbnb.entity.Accommodation;
import com.example.airbnb.entity.AccommodationImage;
import com.example.airbnb.entity.Room;
import com.example.airbnb.entity.User;
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
    public void accommodationSave(RequestAccommodation requestAccommodation
            , List<RequestAccommodationImage> imageList, User user){
        Accommodation accommodation = requestAccommodation.toEntity();
        accommodation.setUser(user);
        List<AccommodationImage> accommodationImages = imageList.stream()
                .map(image -> AccommodationImage.builder()
                        .imageUrl(image.getImageUrl())
                        .accommodation(accommodation)
                        .build())
                .toList();
        List<Room> rooms = requestAccommodation.getRoom().stream()
                .map(requestRoom -> requestRoom.toEntity(accommodation)).toList();

        accommodation.setAccommodationImageList(accommodationImages);
        accommodation.setRoom(rooms);
        Accommodation  savedAccommodation = accommodationRepository.save(accommodation);

        Amenity amenity = requestAccommodation.getAmenity().toAmenity();
        amenity.setAccommodationId(savedAccommodation.getId());
        amenityRepository.save(amenity);
    }
}
