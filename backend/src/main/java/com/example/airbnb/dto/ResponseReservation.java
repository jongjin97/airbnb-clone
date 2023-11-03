package com.example.airbnb.dto;

import com.example.airbnb.document.Reservation;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "예약 ID")
    private String id;
    @ApiModelProperty(value = "숙소 ID")
    private String accommodationId;
    @ApiModelProperty(value = "예약 시작 일자")
    private Date startDate;
    @ApiModelProperty(value = "예약 종료 일자")
    private Date endDate ;
    @ApiModelProperty(value = "예약 상태")
    private String status;
    @ApiModelProperty(value = "총 가격")
    private int totalPrice;
    @ApiModelProperty(value = "숙소 정보")
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
