package com.example.airbnb.service;

import com.example.airbnb.document.Accommodation;
import com.example.airbnb.document.User;
import com.example.airbnb.dto.RequestAccommodation;
import com.example.airbnb.repository.AccommodationRepository;
import com.example.airbnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;
    @Transactional
    public void createAccommodation(RequestAccommodation requestAccommodation, User user) {
        user = userRepository.findById(user.getId()).orElseThrow();
        Accommodation accommodation = requestAccommodation.toAccommodation();
        accommodation.setUser(user);
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);
        user.getAccommodations().add(savedAccommodation);
        userRepository.save(user);
    }

    public List<Accommodation> findAllAccommodation() {
        return accommodationRepository.findAll();
    }
}
