package com.example.airbnb.dto;

import com.example.airbnb.document.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUser {
    private String name;
    private String email;
    private String role;
    private String token;
    public ResponseUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}