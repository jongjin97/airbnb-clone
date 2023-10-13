package com.example.airbnb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Accommodation extends EntityTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 이름
    private String description; // 설명
    private String address; // 주소
    private String city; // 도시
    private String country; // 지역
    private String type;  // 공간전체, 방, 다인실
    private String price; // 가격
    private String status; // N, Y
    private String category; // 카테고리

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    List<AccommodationImage> accommodationImageList; // 이미지 리스트

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    private List<Room> room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void setAccommodationImageList(List<AccommodationImage> accommodationImageList) {
        this.accommodationImageList = accommodationImageList;
    }

    public void setRoom(List<Room> room) {
        this.room = room;
    }
}
