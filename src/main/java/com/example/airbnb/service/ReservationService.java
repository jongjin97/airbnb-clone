package com.example.airbnb.service;

import com.example.airbnb.document.Accommodation;
import com.example.airbnb.document.Reservation;
import com.example.airbnb.document.User;
import com.example.airbnb.dto.RequestReservation;
import com.example.airbnb.dto.ResponseReservation;
import com.example.airbnb.repository.AccommodationRepository;
import com.example.airbnb.repository.ReservationRepository;
import com.example.airbnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final AccommodationRepository  accommodationRepository;
    private final UserRepository userRepository;
    @Transactional
    public void createReservation(RequestReservation requestReservation, User user) {
        Accommodation  accommodation = accommodationRepository.findById(requestReservation.getListingId()).get();
        Reservation reservation = requestReservation.toDocument();
        reservation.setAccommodation(accommodation);
        reservation.setUser(user);

        Reservation savedReservation = reservationRepository.save(reservation);
        accommodation.getReservation().add(savedReservation);
        accommodationRepository.save(accommodation);
        user.getReservations().add(savedReservation);
        userRepository.save(user);
    }
    @Transactional
    public void deleteReservation(String reservationId, User user) {
        Reservation reservation = reservationRepository.findById(reservationId).get();

        Accommodation ac = accommodationRepository.findById(reservation.getAccommodation().getId()).get();
        ac.getReservation().removeIf(res -> res.getId().equals(reservationId));
        accommodationRepository.save(ac);

        user.getReservations().removeIf(res -> res.getId().equals(reservationId));
        userRepository.save(user);

        reservationRepository.delete(reservation);
    }
}

