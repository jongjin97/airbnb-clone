package com.example.airbnb.controller;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.document.Amenity;
import com.example.airbnb.dto.RequestAccommodation;
import com.example.airbnb.entity.Accommodation;
import com.example.airbnb.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/accommodation")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService  accommodationService;

    @PostMapping
    public String createAccommodation(@RequestBody RequestAccommodation accommodation
            , @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("s");
        accommodation.getAccommodation().setUser(userDetails.getUser());
        return accommodationService.AccommodationSave(accommodation.getAccommodation(), accommodation.getAmenity());
    }
}
