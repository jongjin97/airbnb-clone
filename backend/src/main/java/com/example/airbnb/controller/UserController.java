package com.example.airbnb.controller;

import com.example.airbnb.dto.RequestSignin;
import com.example.airbnb.dto.RequestUser;
import com.example.airbnb.dto.ResponseUser;
import com.example.airbnb.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.airbnb.utils.ApiUtils.ApiResult;
import static com.example.airbnb.utils.ApiUtils.success;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ApiResult<ResponseUser> signUp(@RequestBody RequestUser user) throws Exception {
        ResponseUser responseUser = userService.signUp(user);
        return success(responseUser);
    }

    @PostMapping("/signin")
    public ApiResult<ResponseUser> signIn(@RequestBody RequestSignin user, HttpServletResponse response) throws Exception {
        ResponseUser responseUser = userService.signIn(user);
        response.addHeader(AUTHORIZATION, "Bearer " + responseUser.getToken());
        return success(responseUser);
    }
}
