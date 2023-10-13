package com.example.airbnb.dto;

import com.example.airbnb.entity.Accommodation;
import com.example.airbnb.entity.EntityTime;
import com.example.airbnb.entity.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestRoom {
    private int roomNumber;
    private int singleBed;
    private int doubleBed;
    private int queenSizeBed;
    private int kingSizeBed;
    private int twinSizeBed;
    private int bunkBed;

    public Room toEntity(Accommodation accommodation) {
        return Room.builder()
                .roomNumber(roomNumber)
                .singleBed(singleBed)
                .doubleBed(doubleBed)
                .queenSizeBed(queenSizeBed)
                .kingSizeBed(kingSizeBed)
                .twinSizeBed(twinSizeBed)
                .bunkBed(bunkBed)
                .accommodation(accommodation)
                .build();

    }
}
