package com.example.airbnb.repository;

import com.example.airbnb.document.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByAccommodationId(String accommodationId);
}
