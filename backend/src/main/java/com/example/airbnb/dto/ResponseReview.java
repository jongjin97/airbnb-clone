package com.example.airbnb.dto;

import com.example.airbnb.document.Review;
import com.example.airbnb.document.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseReview {
    @ApiModelProperty(value = "리뷰 평점", example = "5")
    private int rating;
    @ApiModelProperty(value = "리뷰 메시지", example = "좋아요")
    private String message;
    @ApiModelProperty(value = "리뷰 작성자")
    private ResponseUser responseUser;

    public ResponseReview(Review review) {
        this.rating = review.getRating();
        this.message = review.getMessage();
        this.responseUser = new ResponseUser(review.getUser());
    }
}
