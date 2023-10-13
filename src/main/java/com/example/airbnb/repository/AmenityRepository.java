package com.example.airbnb.repository;

import com.example.airbnb.document.Amenity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityRepository extends MongoRepository<Amenity, Long>
{
    public Amenity findById(String id);
}
