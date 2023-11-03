package com.example.airbnb.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestLocation {
    private String flag;
    private String label;
    private int[] latlng;
    private String region;
    private String value;

}
