package com.example.airbnb.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUser {
    private String name;
    private String email;
    private String password;
    private String role;
}
