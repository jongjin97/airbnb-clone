package com.example.airbnb.controller;

import com.example.airbnb.dto.RequestUser;
import com.example.airbnb.config.auth.UserDetailsServiceImpl;
import com.example.airbnb.dto.ResponseUser;
import com.example.airbnb.service.UserService;
import com.example.airbnb.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.airbnb.utils.ApiUtils.ApiResult;
import static com.example.airbnb.utils.ApiUtils.ApiResult.*;
import static com.example.airbnb.utils.ApiUtils.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ApiResult<ResponseUser> create(@RequestBody RequestUser user) throws Exception {
        ResponseUser responseUser = userService.signUp(user);
        return success(responseUser);
    }

//    @PostMapping("siginin")
//    public void signIn(@RequestBody RequestUser user) throws Exception {
//        userService.signIn(user);
//    }
}
