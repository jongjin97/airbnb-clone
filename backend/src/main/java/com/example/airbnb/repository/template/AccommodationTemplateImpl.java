package com.example.airbnb.repository.template;

import com.example.airbnb.document.Accommodation;
import com.example.airbnb.document.Reservation;
import com.example.airbnb.document.User;
import com.example.airbnb.repository.UserRepository;
import com.mongodb.BasicDBObject;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        if (accommodationParam.containsKey("startDate") && accommodationParam.containsKey("endDate")) {
            String startDateStr = accommodationParam.get("startDate");
            String endDateStr = accommodationParam.get("endDate");

            Date startDate = parseDate(startDateStr);
            Date endDate = parseDate(endDateStr);

            // startDate와 endDate 사이에 예약이 없는 숙소를 검색합니다.
            Criteria reservationCriteria = new Criteria().orOperator(
                    Criteria.where("reservations").size(0), // 예약이 없는 경우
                    Criteria.where("reservations.startDate").gt(endDate), // 예약 시작일이 endDate 이후인 경우
                    Criteria.where("reservations.endDate").lt(startDate) // 예약 종료일이 startDate 이전인 경우
            );

            query.addCriteria(reservationCriteria);
        }
        return mongoTemplate.find(query, Accommodation.class);
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
