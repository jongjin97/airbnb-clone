package com.example.airbnb.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestReview {
    @ApiModelProperty(value = "평점", example = "5.0")
    private String rating;
    @ApiModelProperty(value = "리뷰 내용", example = "좋아요")
    private String message;
}
