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
//@Api(tags = "User")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
//    @ApiOperation(value = "회원가입")
//    @ApiImplicitParams(value = {
//            @ApiImplicitParam(name = "user", value = "회원가입 정보", required = true, dataType = "RequestUser")
//    })
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "회원가입 성공")
//            ,@ApiResponse(code = 400, message = "회원가입 실패")
//            ,@ApiResponse(code = 409,  message = "이미 존재하는 회원입니다.")
//    })
    //@ApiResponses(value = {
    public ApiResult<ResponseUser> signUp(@RequestBody RequestUser user) throws Exception {
        ResponseUser responseUser = userService.signUp(user);
        return success(responseUser);
    }

    @PostMapping("/signin")
//    @ApiOperation(value = "로그인")
//    @ApiImplicitParams(value = {
//            @ApiImplicitParam(name = "user", value = "로그인 정보", required = true, dataType = "RequestSignin")
//            ,@ApiImplicitParam(name = "response", value = "응답 객체", required = true, dataType = "HttpServletResponse")
//    })
//    @ApiResponse(code = 200, message = "로그인 성공")
    public ApiResult<ResponseUser> signIn(@RequestBody RequestSignin user, HttpServletResponse response) throws Exception {
        ResponseUser responseUser = userService.signIn(user);
        response.addHeader(AUTHORIZATION, "Bearer " + responseUser.getToken());
        return success(responseUser);
    }
}
