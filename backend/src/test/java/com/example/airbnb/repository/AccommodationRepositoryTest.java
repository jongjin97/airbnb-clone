package com.example.airbnb.repository;

import com.example.airbnb.document.Accommodation;
import com.example.airbnb.document.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class AccommodationRepositoryTest {

    @Autowired
    private AccommodationRepository accommodationRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void test(){
        // 조건을 설정합니다.
        Map<String,String> map = new HashMap();
        map.put("startDate", "2024-11-6T00:00:00+09:00");
        map.put("endDate", "2024-11-8T00:00:00+09:00");
        // Query를 생성하여 조건을 적용합니다.
        Query query = new Query();

        query.addCriteria(Criteria.where("accommodationId").is("6534f46e61ad2253e6680f01"));
        Criteria reservationCriteria = new Criteria().orOperator(
                Criteria.where("startDate").gte(map.get("endDate")), // 예약 시작일이 endDate 이후인 경우
                Criteria.where("endDate").lte(map.get("startDate")) // 예약 종료일이 startDate 이전인 경우
        );
        query.addCriteria(reservationCriteria);
        // MongoDB에서 문서를 검색합니다.
        List<Reservation> accommodations = mongoTemplate.find(query, Reservation.class);

        assertEquals(1, accommodations.size());
    }
    @Test
    void findByCategoryAndGuestCountGreaterThanEqualAndBathroomCountGreaterThanEqualAndRoomCountGreaterThanEqualAndLocation_Value() {
        List<Accommodation> list = accommodationRepository.findByCategoryAndGuestCountGreaterThanEqualAndBathroomCountGreaterThanEqualAndRoomCountGreaterThanEqualAndLocation_Value(null, 0, 0, 0, null);
        assertEquals(1, list.size());
    }

    @Test
    void findAccommodationByParam() {
        Map<String, String> map = new HashMap<>();
        map.put("startDate", "2023-11-6T00:00:00+09:00");
        map.put("endDate", "2023-11-8T00:00:00+09:00");
        List<Accommodation> lIst = accommodationRepository.findAllByParam(map);
        assertEquals(1, lIst.size());
    }

}