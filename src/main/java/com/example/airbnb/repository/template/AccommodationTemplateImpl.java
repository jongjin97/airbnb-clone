package com.example.airbnb.repository.template;

import com.example.airbnb.document.Accommodation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AccommodationTemplateImpl implements AccommodationTemplate{

    private final MongoTemplate mongoTemplate;
    @Override
    public List<Accommodation> findAllByParam(Map<String, String> accommodationParam) {
        Query query = new Query();
        if(accommodationParam.isEmpty())
            return mongoTemplate.findAll(Accommodation.class);

        if (accommodationParam.get("category") != null) {
            query.addCriteria(Criteria.where("category").is(accommodationParam.get("category")));
        }

        if (accommodationParam.get("roomCount") != null && Integer.parseInt(accommodationParam.get("roomCount")) > 0) {
            query.addCriteria(Criteria.where("roomCount").gte(accommodationParam.get("roomCount")));
        }

        if (accommodationParam.get("guestCount") != null && Integer.parseInt(accommodationParam.get("guestCount")) > 0) {
            query.addCriteria(Criteria.where("guestCount").gte(accommodationParam.get("guestCount")));
        }

        if (accommodationParam.get("bathroomCount") != null && Integer.parseInt(accommodationParam.get("bathroomCount")) > 0) {
            query.addCriteria(Criteria.where("bathroomCount").gte(accommodationParam.get("bathroomCount")));
        }
        if (accommodationParam.get("locationValue") != null) {
            query.addCriteria(Criteria.where("location.Value").is(accommodationParam.get("locationValue")));
        }
        if (accommodationParam.get("startDate") != null && accommodationParam.get("endDate") != null){
            Criteria dateRange1 = Criteria.where("reservations.endDate").gte(accommodationParam.get("startDate"))
                    .andOperator(Criteria.where("reservations.startDate").lte(accommodationParam.get("startDate")));

            Criteria dateRange2 = Criteria.where("reservations.startDate").lte(accommodationParam.get("endDate"))
                    .andOperator(Criteria.where("reservations.endDate").gte(accommodationParam.get("endDate")));

            Criteria reservationNotOverlap = new Criteria().orOperator(dateRange1, dateRange2);

            query.addCriteria(new Criteria().norOperator(reservationNotOverlap));
        }
        return mongoTemplate.find(query, Accommodation.class);
    }
}
