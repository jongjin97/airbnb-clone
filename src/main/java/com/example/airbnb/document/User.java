package com.example.airbnb.entity;

import com.example.airbnb.dto.RequestUser;
import com.example.airbnb.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Table(name = "user")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends EntityTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;


    public User(RequestUser requestUser){
        this.name = requestUser.getName();
        this.email = requestUser.getEmail();
        this.password = requestUser.getPassword();
        this.role = Role.ROLE_USER.getRole();
    }
}
