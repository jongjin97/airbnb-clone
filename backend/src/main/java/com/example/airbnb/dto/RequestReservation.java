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
    private String listingId;
    private Date startDate;
    private Date endDate;
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
