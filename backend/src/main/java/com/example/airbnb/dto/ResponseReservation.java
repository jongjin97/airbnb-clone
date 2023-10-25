package com.example.airbnb.dto;

import com.example.airbnb.document.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseReservation {
    private String id;
    private String accommodationId;
    private Date startDate;
    private Date endDate ;
    private String status;
    private int totalPrice;
    private ResponseAccommodation listing;
    public ResponseReservation(Reservation reservation) {
        this.id = reservation.getId();
        this.accommodationId = reservation.getAccommodation().getId();
        this.startDate = reservation.getStartDate();
        this.endDate  = reservation.getEndDate();
        this.status = reservation.getStatus();
        this.totalPrice = reservation.getTotalPrice();
    }
}
