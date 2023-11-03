package com.example.airbnb.dto;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSignin {
    @NotNull
    @NotBlank
    @Email
    @ApiModelProperty(value = "email", required = true)
    private String email;
    @NotNull
    @NotBlank
    @ApiModelProperty(value = "password", required = true)
    private String password;
}
