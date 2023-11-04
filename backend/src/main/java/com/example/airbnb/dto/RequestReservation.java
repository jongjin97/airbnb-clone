package com.example.airbnb.dto;

import com.example.airbnb.document.Reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestReservation {
    //   @ApiModelProperty(value = "숙소 id")
    private String listingId;
    //  @ApiModelProperty(value = "예약 시작 일자")
    private Date startDate;
    //  @ApiModelProperty(value = "예약 종료 일자")
    private Date endDate;
    //  @ApiModelProperty(value = "총 가격")
    private int totalPrice;

    public Reservation toDocument(){
        return Reservation.builder()
                .accommodationId(listingId)
                .startDate(startDate)
                .endDate(endDate)
                .totalPrice(totalPrice)
                .build();
    }
}
