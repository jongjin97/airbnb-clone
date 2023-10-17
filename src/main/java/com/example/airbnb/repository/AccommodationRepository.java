package com.example.airbnb.repository;

import com.example.airbnb.document.Accommodation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends MongoRepository<Accommodation, String> {
    Accommodation findAccommodationById(String id);
}
