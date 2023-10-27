package com.example.airbnb.service;

import com.example.airbnb.document.Review;
import com.example.airbnb.document.User;
import com.example.airbnb.dto.RequestReview;
import com.example.airbnb.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository repository;

    public void saveReview(String id, RequestReview requestReview, User user){
        Review review = Review.builder()
                .accommodationId(id)
                .user(user)
                .message(requestReview.getMessage())
                .rating(Integer.parseInt(requestReview.getRating()))
                .build();

        repository.save(review);
    }

}
