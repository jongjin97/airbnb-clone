package com.example.airbnb.dto;

import com.example.airbnb.document.Accommodation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccommodation {
    //    @ApiModelProperty(value = "숙소 침실 수", example = "2")
    private int bedCount;
    //    @ApiModelProperty(value = "숙소 욕실 수", example = "2")
    private int bathroomCount;
    //    @ApiModelProperty(value = "숙소 카테고리", example = "숙소")
    private String category;
    //   @ApiModelProperty(value = "숙소 설명", example = "숙소 설명")
    private String description;
    //   @ApiModelProperty(value = "숙소 제목", example = "숙소 제목")
    private String title;
    //   @ApiModelProperty(value = "숙소 시설", example = "숙소 시설")
    private List<String> facility;
    //  @ApiModelProperty(value = "숙소 인원 수", example = "2")
    private int guestCount;
    //   @ApiModelProperty(value = "숙소 방 수", example = "2")
    private int roomCount;
    //  @ApiModelProperty(value = "숙소 이미지 주소", example = "숙소 이미지 주소")
    private List<List<String>> imageSrc;
    //    @ApiModelProperty(value = "숙소 위치", example = "숙소 위치")
    private RequestLocation location;
    //    @ApiModelProperty(value = "숙소 가격", example = "2")
    private int price;
    public Accommodation toAccommodation(){
        return Accommodation.builder()
                .bathroomCount(bathroomCount)
                .category(category)
                .description(description)
                .facility(facility)
                .guestCount(guestCount)
                .title(title)
                .roomCount(roomCount)
                .imageSrc(imageSrc)
                .location(location)
                .price(price)
                .build();
    }
}
