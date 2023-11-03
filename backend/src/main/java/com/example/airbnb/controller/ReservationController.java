package com.example.airbnb.controller;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.document.User;
import com.example.airbnb.dto.RequestReservation;
import com.example.airbnb.dto.ResponseAccommodation;
import com.example.airbnb.dto.ResponseReservation;
import com.example.airbnb.service.FileService;
import com.example.airbnb.service.ReservationService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.airbnb.utils.ApiUtils.ApiResult;
import static com.example.airbnb.utils.ApiUtils.success;
@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
@Api(tags = "Reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final FileService fileService;
    @PostMapping
    @ApiImplicitParams(value = {
             @ApiImplicitParam(name = "reservation", value = "예약 정보", required = true, dataType = "RequestReservation")
            ,@ApiImplicitParam(name = "userDetails", value = "유저 정보", required = true, dataType = "UserDetailsImpl")
    })
    @ApiResponses(value = {
             @ApiResponse(code = 200, message = "예약 성공")
            ,@ApiResponse(code = 400, message = "예약 실패")
    })
    @ApiOperation (value = "예약 생성", notes = "예약 생성")
    public ApiResult<String> createReservation(@RequestBody RequestReservation requestReservation
            , @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reservationService.createReservation(requestReservation,  userDetails.getUser());
        return success("success");
    }
    @DeleteMapping("/{id}")
    @ApiImplicitParams(value = {
             @ApiImplicitParam(name = "id", value = "예약 id", required = true, dataType = "String")
            ,@ApiImplicitParam(name = "userDetails", value = "유저 정보", required = true, dataType = "UserDetailsImpl")
    })
    @ApiResponses(value = {
             @ApiResponse(code = 200, message = "예약 삭제 성공")
            ,@ApiResponse(code = 400, message = "예약 삭제 실패")
    })
    @ApiOperation (value = "예약 삭제", notes = "예약 삭제")
    public ApiResult<String> deleteReservation(@PathVariable String id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reservationService.deleteReservation(id, userDetails.getUser());
        return success("success");
    }
    @GetMapping
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userDetails", value = "유저 정보", required = true, dataType = "UserDetailsImpl")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "예약 조회 성공")
            ,@ApiResponse(code = 400, message = "예약 조회 실패")
    })
    @ApiOperation (value = "예약 조회", notes = "예약 조회")
    public ApiResult<List<ResponseReservation>> getReservations(@AuthenticationPrincipal UserDetailsImpl userDetails)
            throws Exception {
        User user = userDetails.getUser();
        List<ResponseReservation> responseReservationList = user.getReservations().stream()
                .map(reservation -> {
                    try {
                        ResponseReservation responseReservation = new ResponseReservation(reservation);
                        responseReservation.setListing(new ResponseAccommodation(reservation.getAccommodation()));
                        List<byte[]> imageByte =fileService.downloadFile(responseReservation.getListing().getImageSrc());
                        responseReservation.getListing().setImageByte(imageByte);
                        return responseReservation;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toList();

        return success(responseReservationList);
    }
}
