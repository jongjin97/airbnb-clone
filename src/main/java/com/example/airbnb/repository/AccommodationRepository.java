package com.example.airbnb.repository;

import com.example.airbnb.document.Accommodation;
import com.example.airbnb.repository.template.AccommodationTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends MongoRepository<Accommodation, String>, AccommodationTemplate {
    List<Accommodation> findByCategoryAndGuestCountGreaterThanEqualAndBathroomCountGreaterThanEqualAndRoomCountGreaterThanEqualAndLocation_Value(String category, int guestCount, int bathroomCount, int roomCount, String value);
    Accommodation findAccommodationById(String id);

}
