package com.example.airbnb.repository.template;

import com.example.airbnb.document.Accommodation;
import com.example.airbnb.document.Reservation;
import com.example.airbnb.document.User;
import com.example.airbnb.repository.UserRepository;
import com.mongodb.BasicDBObject;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class AccommodationTemplateImpl implements AccommodationTemplate{

    private final MongoTemplate mongoTemplate;
    private final UserRepository userRepository;
    @Override
    public List<Accommodation> findAllByParam(Map<String, String> accommodationParam) {
        Query query = new Query();

        if(accommodationParam.isEmpty())
            return mongoTemplate.findAll(Accommodation.class);

        if (accommodationParam.get("category") != null) {
            query.addCriteria(Criteria.where("category").is(accommodationParam.get("category")));
        }

        if (accommodationParam.get("roomCount") != null && Integer.parseInt(accommodationParam.get("roomCount")) > 0) {
            query.addCriteria(Criteria.where("roomCount").gte(Integer.parseInt(accommodationParam.get("roomCount"))));
        }

        if (accommodationParam.get("guestCount") != null && Integer.parseInt(accommodationParam.get("guestCount")) > 0) {
            query.addCriteria(Criteria.where("guestCount").gte(Integer.parseInt(accommodationParam.get("guestCount"))));
        }

        if (accommodationParam.get("bathroomCount") != null && Integer.parseInt(accommodationParam.get("bathroomCount")) > 0) {
            query.addCriteria(Criteria.where("bathroomCount").gte(Integer.parseInt(accommodationParam.get("bathroomCount"))));
        }
        if (accommodationParam.get("locationValue") != null) {
            query.addCriteria(Criteria.where("location.value").is(accommodationParam.get("locationValue")));
        }
        List<Accommodation> accommodationList = mongoTemplate.find(query, Accommodation.class);
        List<Accommodation> result = new ArrayList<>();
        if (accommodationParam.containsKey("startDate") && accommodationParam.containsKey("endDate")) {

            String startDateStr = accommodationParam.get("startDate");
            String endDateStr = accommodationParam.get("endDate");

            Date startDate = parseDate(startDateStr);
            Date endDate = parseDate(endDateStr);

            for(Accommodation accommodation: accommodationList){
                query = new Query();
                Criteria reservationCriteria = new Criteria().orOperator(
                        Criteria.where("startDate").gte(startDate).lte(endDate),
                        Criteria.where("endDate").gte(startDate).lte(endDate)
                );
                query.addCriteria(Criteria.where("accommodationId").is(accommodation.getId()));
                query.addCriteria(reservationCriteria);
                List<Reservation> reservations = mongoTemplate.find(query, Reservation.class);
                if(reservations.isEmpty())
                    result.add(accommodation);
            }
            return result;
        }
        return accommodationList;
    }
    private Date parseDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            // 날짜 파싱 실패 시 처리할 로직 추가
            return null;
        }
    }
    @Override
    public void AlldeleteAccomById(String id) {
        Accommodation accommodation = mongoTemplate.findById(id, Accommodation.class);

        Query reservationQuery = new Query(Criteria.where("accommodation.$id").is(new ObjectId(id)));
        List<Reservation> reservations = mongoTemplate.findAllAndRemove(reservationQuery, Reservation.class);

        // 관련된 사용자의 reservations 필드 업데이트
        for (Reservation reservation : reservations) {
            User user = reservation.getUser();
            user.getReservations().removeIf(r -> r.getId().equals(reservation.getId()));
            userRepository.save(user);
        }

        Query userQuery = new Query(Criteria.where("accommodation.$id").is(new ObjectId(id)));
        Update userUpdate = new Update().pull("accommodation", new BasicDBObject("$id", new ObjectId(id)));
        mongoTemplate.updateMulti(userQuery, userUpdate, User.class);

        Query userQuery2 = new Query(Criteria.where("favorites").is(id));
        Update userUpdate2 = new Update().pull("favorites", id);
        mongoTemplate.updateMulti(userQuery2, userUpdate2, User.class);

        mongoTemplate.remove(accommodation);
    }
}
