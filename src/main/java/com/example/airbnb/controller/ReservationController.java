package com.example.airbnb.controller;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.dto.RequestReservation;
import com.example.airbnb.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.example.airbnb.utils.ApiUtils.ApiResult;
import static com.example.airbnb.utils.ApiUtils.success;
@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/{pdid}")
    public ApiResult<String> createReservation(@RequestBody RequestReservation requestReservation
            , @PathVariable("pdid") Long pdid
            , @AuthenticationPrincipal UserDetailsImpl userDetails) {
        requestReservation.setUser(userDetails.getUser());
        requestReservation.setAccommodationId(pdid);
        reservationService.createReservation(requestReservation);
        return success("예약이 완료되었습니다.");
    }
}
