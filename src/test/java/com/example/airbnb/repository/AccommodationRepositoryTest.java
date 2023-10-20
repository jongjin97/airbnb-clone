package com.example.airbnb.repository;

import com.example.airbnb.document.Accommodation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class AccommodationRepositoryTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Test
    void findByCategoryAndGuestCountGreaterThanEqualAndBathroomCountGreaterThanEqualAndRoomCountGreaterThanEqualAndLocation_Value() {
        List<Accommodation> list = accommodationRepository.findByCategoryAndGuestCountGreaterThanEqualAndBathroomCountGreaterThanEqualAndRoomCountGreaterThanEqualAndLocation_Value(null, 0, 0, 0, null);
        assertEquals(1, list.size());
    }

    @Test
    void findAccommodationById() {
        List<Accommodation> lIst = accommodationRepository.findAll();
        assertEquals(1, lIst.size());
    }

}