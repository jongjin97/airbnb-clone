package com.example.airbnb.document;

import com.example.airbnb.dto.RequestUser;
import com.example.airbnb.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;


    public User(RequestUser requestUser){
        this.name = requestUser.getName();
        this.email = requestUser.getEmail();
        this.password = requestUser.getPassword();
        this.role = Role.ROLE_USER.getRole();
    }
}
