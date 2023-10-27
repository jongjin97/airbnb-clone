package com.example.airbnb.service;

import com.example.airbnb.document.Accommodation;
import com.example.airbnb.document.Review;
import com.example.airbnb.document.User;
import com.example.airbnb.dto.RequestAccommodation;
import com.example.airbnb.dto.ResponseAccommodation;
import com.example.airbnb.dto.ResponseReview;
import com.example.airbnb.repository.AccommodationRepository;
import com.example.airbnb.repository.ReservationRepository;
import com.example.airbnb.repository.ReviewRepository;
import com.example.airbnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final ReviewRepository reviewRepository;
    private final FileService fileService;
    @Transactional
    public void createAccommodation(RequestAccommodation requestAccommodation, User user) {
        user = userRepository.findById(user.getId()).orElseThrow();
        Accommodation accommodation = requestAccommodation.toAccommodation();
        accommodation.setUser(user);
        accommodation.setReservations(new ArrayList<>());
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);
        user.getAccommodation().add(savedAccommodation);
        userRepository.save(user);
    }
    @Transactional
    public List<ResponseAccommodation> findAllByParam(Map<String, String> accommodationParam) throws Exception {
        List<Accommodation> accommodations = accommodationRepository.findAllByParam(accommodationParam);
        List<ResponseAccommodation> responseAccommodations = accommodations.stream().map(ResponseAccommodation::new).toList();
        responseAccommodations.forEach(responseAccommodation -> {
            List<Review> reviews = reviewRepository.findByAccommodationId(responseAccommodation.getId());
            List<ResponseReview> responseReviews = reviews.stream().map(ResponseReview::new).toList();
            responseAccommodation.setReview(responseReviews);
            responseAccommodation.setAverage(calculateAverageRating(responseAccommodation.getReview()));
        });
        for(ResponseAccommodation responseAccommodation: responseAccommodations){
                responseAccommodation.setImageByte(fileService.downloadFile(responseAccommodation.getImageSrc()));
        }
        return responseAccommodations;
    }
    @Transactional
    public ResponseAccommodation findById(String id) throws Exception {
        Accommodation accommodation = accommodationRepository.findById(id).orElseThrow();
        List<Review> reviewList = reviewRepository.findByAccommodationId(id);
        List<ResponseReview> responseReviewList = reviewList.stream().map(ResponseReview::new).toList();
        ResponseAccommodation responseAccommodation = new ResponseAccommodation(accommodation);
        responseAccommodation.setImageByte(fileService.downloadFile(responseAccommodation.getImageSrc()));
        responseAccommodation.setReview(responseReviewList);
        return responseAccommodation;
    }

    @Transactional
    public void deleteAccommodation(String id) throws Exception {
        accommodationRepository.AlldeleteAccomById(id);
    }

    public static double calculateAverageRating(List<ResponseReview> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (ResponseReview review : reviews) {
            sum += review.getRating();
        }

        return sum / reviews.size();
    }
}
