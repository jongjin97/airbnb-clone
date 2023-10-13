package com.example.airbnb.dto;

import com.example.airbnb.entity.Accommodation;
import com.example.airbnb.entity.Reservation;
import com.example.airbnb.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestReservation {
    private Long accommodationId;

    private User user;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int adults;

    private int children;

    private int infants;

    public Reservation toEntity(Accommodation accommodation) {
        return Reservation.builder()
                .user(user)
                .accommodation(accommodation)
                .checkInDate(checkInDate)
                .checkOutDate(checkOutDate)
                .adults(adults)
                .children(children)
                .infants(infants)
                .build();
    }
}
