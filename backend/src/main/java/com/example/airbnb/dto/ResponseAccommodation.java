package com.example.airbnb.dto;

import com.example.airbnb.document.Accommodation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAccommodation {
    //  @ApiModelProperty(value = "숙소 ID")
    private String id;
    //  @ApiModelProperty(value = "숙소 욕실 수")
    private int bathroomCount;
    //  @ApiModelProperty(value = "숙소 카테고리")
    private String category;
    //  @ApiModelProperty(value = "숙소 제목")
    private String title;
    //  @ApiModelProperty(value = "숙소 설명")
    private String description;
    //   @ApiModelProperty(value = "숙소 시설")
    private List<String> facility;
    //   @ApiModelProperty(value = "숙소 인원 수")
    private int guestCount;
    //   @ApiModelProperty(value = "숙소 방 수")
    private int roomCount;
    //   @ApiModelProperty(value = "숙소 이미지")
    private List<List<String>> imageSrc;
    //   @ApiModelProperty(value = "숙소 이미지 바이트")
    private List<byte[]> imageByte;
    //  @ApiModelProperty(value = "숙소 위치")
    private RequestLocation location;
    //  @ApiModelProperty(value = "숙소 가격")
    private int price;
    //  @ApiModelProperty(value = "숙소 작성자")
    private ResponseUser user;
    // @ApiModelProperty(value = "숙소 예약")
    private List<ResponseReservation> reservation;
    //   @ApiModelProperty(value = "숙소 리뷰")
    private List<ResponseReview> review;
    //    @ApiModelProperty(value = "숙소 평균 평점")
    private double average;
    public ResponseAccommodation(Accommodation accommodation) {
        this.id = accommodation.getId();
        this.bathroomCount = accommodation.getBathroomCount();
        this.category = accommodation.getCategory();
        this.title = accommodation.getTitle();
        this.description = accommodation.getDescription();
        this.facility = accommodation.getFacility();
        this.guestCount = accommodation.getGuestCount();
        this.roomCount = accommodation.getRoomCount();
        this.location = accommodation.getLocation();
        this.price = accommodation.getPrice();
        this.imageSrc = accommodation.getImageSrc();
        this.user = new ResponseUser(accommodation.getUser());
        this.reservation = accommodation.getReservations() == null ? null : accommodation.getReservations().stream().map(ResponseReservation::new).toList();
        // convert Reservation to ResponseReservation (recursive call)
    }
}
