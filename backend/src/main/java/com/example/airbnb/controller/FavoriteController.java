package com.example.airbnb.controller;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.dto.ResponseAccommodation;
import com.example.airbnb.dto.ResponseUser;
import com.example.airbnb.service.FavoriteService;
import io.swagger.annotations.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.airbnb.utils.ApiUtils.ApiResult;
import static com.example.airbnb.utils.ApiUtils.success;
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
@RestController
@Api(tags = "Favorite")
public class FavoriteController {
    private final FavoriteService  favoriteService;

    @PostMapping("/{id}")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "숙소 id", required = true, dataType = "String")
            ,@ApiImplicitParam(name = "userDetails", value = "로그인 유저 정보", required = true, dataType = "UserDetailsImpl")
    })
    @ApiResponses(value = {
             @ApiResponse(code = 200, message = "즐겨찾기 성공")
            ,@ApiResponse(code = 400, message = "즐겨찾기 실패")
    })
    @ApiOperation(value = "즐겨찾기 추가")
    public ApiResult<ResponseUser> addFavorite(@PathVariable String id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return success(favoriteService.addFavorite(id, userDetails.getUser()));
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "숙소 id", required = true, dataType = "String")
            ,@ApiImplicitParam(name = "userDetails", value = "로그인 유저 정보", required = true, dataType = "UserDetailsImpl")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "즐겨찾기 삭제 성공")
            ,@ApiResponse(code = 400, message = "즐겨찾기 삭제 실패")
    })
    @ApiOperation(value = "즐겨찾기 삭제")
    public ApiResult<ResponseUser> deleteFavorite(@PathVariable String id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return success(favoriteService.removeFavorite(id, userDetails.getUser()));
    }

    @GetMapping
    @ApiImplicitParam( name = "userDetails", value = "로그인 유저 정보", required = true, dataType = "UserDetailsImpl")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "즐겨찾기 조회 성공")
            ,@ApiResponse(code = 400, message = "즐겨찾기 조회 실패")
    })
    @ApiOperation(value = "즐겨찾기 조회")
    public ApiResult<List<ResponseAccommodation>> getFavorite(@AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        return success(favoriteService.getFavorites(userDetails.getUser()));
    }
}
