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
    private String id;
    private int bathroomCount;
    private String category;
    private String title;
    private String description;
    private List<String> facility;
    private int guestCount;
    private int roomCount;
    private List<List<String>> imageSrc;
    private List<byte[]> imageByte;
    private RequestLocation location;
    private int price;
    private ResponseUser user;
    private List<ResponseReservation> reservation;
    private List<ResponseReview> review;
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
