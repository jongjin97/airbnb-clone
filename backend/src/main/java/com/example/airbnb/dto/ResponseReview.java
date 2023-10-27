package com.example.airbnb.dto;

import com.example.airbnb.document.Review;
import com.example.airbnb.document.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseReview {
    private int rating;
    private String message;
    private ResponseUser responseUser;

    public ResponseReview(Review review) {
        this.rating = review.getRating();
        this.message = review.getMessage();
        this.responseUser = new ResponseUser(review.getUser());
    }
}
