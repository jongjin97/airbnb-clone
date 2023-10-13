package com.example.airbnb.service;

import com.example.airbnb.dto.RequestReservation;
import com.example.airbnb.entity.Accommodation;
import com.example.airbnb.entity.User;
import com.example.airbnb.errors.NotFoundException;
import com.example.airbnb.repository.AccommodationRepository;
import com.example.airbnb.repository.ReservationRepository;
import com.example.airbnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;
    public void createReservation(RequestReservation requestReservation) {
        Accommodation accommodation = accommodationRepository.findById(requestReservation.getAccommodationId())
                .orElseThrow(() -> new NotFoundException("숙소 정보가 없습니다."));

        reservationRepository.save(requestReservation.toEntity(accommodation));
    }
}
