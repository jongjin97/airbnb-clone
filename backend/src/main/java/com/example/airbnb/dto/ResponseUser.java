package com.example.airbnb.dto;

import com.example.airbnb.document.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUser {
    @ApiModelProperty(value = "유저 아이디", example = "1", required = true)
    private String id;
    @ApiModelProperty(value = "유저 이름", example = "유저", required = true)
    private String name;
    @ApiModelProperty(value = "유저 이메일", required = true)
    private String email;
    @ApiModelProperty(value = "유저 권한", example = "USER", required = true)
    private String role;
    @ApiModelProperty(value = "유저 토큰", example = "XXXXXXXXXXXXXX", required = true)
    private String token;
    @ApiModelProperty(value = "유저 즐겨찾기", required = true)
    private List<String> favorites;
    public ResponseUser(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.favorites = user.getFavorites();
    }
}