package com.example.airbnb.repository;

import com.example.airbnb.document.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
    List<Reservation> findByUser_Id(String id);
}
