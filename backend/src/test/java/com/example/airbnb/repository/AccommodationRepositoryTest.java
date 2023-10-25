package com.example.airbnb.repository;

import com.example.airbnb.document.Accommodation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    void findAccommodationByParam() {
        Map<String, String> map = new HashMap<>();
        map.put("guestCount", "1");
        map.put("bathroomCount", "1");
        map.put("roomCount", "1");
        map.put("startDate", "2023-10-24T00:00:00+09:00");
        map.put("endDate", "2023-10-25T00:00:00+09:00");
        List<Accommodation> lIst = accommodationRepository.findAllByParam(map);
        assertEquals(1, lIst.size());
    }

}