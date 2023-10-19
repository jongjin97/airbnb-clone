package com.example.airbnb.repository;

import com.example.airbnb.document.Accommodation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends MongoRepository<Accommodation, String> {
    List<Accommodation> findByCategoryAndGuestCountGreaterThanEqualAndBathroomCountGreaterThanEqualAndRoomCountGreaterThanEqualAndLocation_Value(String category, int guestCount, int bathroomCount, int roomCount, String value);
    Accommodation findAccommodationById(String id);

}
