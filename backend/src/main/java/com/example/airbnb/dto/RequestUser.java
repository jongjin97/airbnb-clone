package com.example.airbnb.dto;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUser {
    //   @ApiModelProperty(value = "이름")
    private String name;
    //  @ApiModelProperty(value = "이메일")
    private String email;
    //  @ApiModelProperty(value = "비밀번호")
    private String password;
    //  @ApiModelProperty(value = "역할")
    private String role;
}
