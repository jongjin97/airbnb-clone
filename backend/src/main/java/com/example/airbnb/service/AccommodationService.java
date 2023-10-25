package com.example.airbnb.service;

import com.example.airbnb.document.Accommodation;
import com.example.airbnb.document.User;
import com.example.airbnb.dto.RequestAccommodation;
import com.example.airbnb.dto.ResponseAccommodation;
import com.example.airbnb.repository.AccommodationRepository;
import com.example.airbnb.repository.ReservationRepository;
import com.example.airbnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final FileService fileService;
    @Transactional
    public void createAccommodation(RequestAccommodation requestAccommodation, User user) {
        user = userRepository.findById(user.getId()).orElseThrow();
        Accommodation accommodation = requestAccommodation.toAccommodation();
        accommodation.setUser(user);
        accommodation.setReservations(new ArrayList<>());
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);
        user.getAccommodation().add(savedAccommodation);
        userRepository.save(user);
    }
    @Transactional
    public List<ResponseAccommodation> findAllByParam(Map<String, String> accommodationParam) throws Exception {
        List<Accommodation> accommodations = accommodationRepository.findAllByParam(accommodationParam);
        List<ResponseAccommodation> responseAccommodations = accommodations.stream().map(ResponseAccommodation::new).toList();
        for(ResponseAccommodation responseAccommodation: responseAccommodations){
                responseAccommodation.setImageByte(fileService.downloadFile(responseAccommodation.getImageSrc()));
        }
        return responseAccommodations;
    }
    @Transactional
    public ResponseAccommodation findById(String id) throws Exception {
        Accommodation accommodation = accommodationRepository.findById(id).orElseThrow();
        ResponseAccommodation responseAccommodation = new ResponseAccommodation(accommodation);
        responseAccommodation.setImageByte(fileService.downloadFile(responseAccommodation.getImageSrc()));
        return responseAccommodation;
    }

    @Transactional
    public void deleteAccommodation(String id) throws Exception {
        accommodationRepository.AlldeleteAccomById(id);
    }
}