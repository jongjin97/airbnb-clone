package com.example.airbnb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChatRoom {
    private String id;
    private ResponseUser user;
    private ResponseUser user2;
}
