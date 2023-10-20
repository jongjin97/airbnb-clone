package com.example.airbnb.controller;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.dto.ResponseUser;
import com.example.airbnb.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.example.airbnb.utils.ApiUtils.ApiResult;
import static com.example.airbnb.utils.ApiUtils.success;
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
@RestController
public class FavoriteController {
    private final FavoriteService  favoriteService;

    @PostMapping("/{id}")
    public ApiResult<ResponseUser> addFavorite(@PathVariable String id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return success(favoriteService.addFavorite(id, userDetails.getUser()));
    }

    @DeleteMapping("/{id}")
    public ApiResult<ResponseUser> deleteFavorite(@PathVariable String id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return success(favoriteService.removeFavorite(id, userDetails.getUser()));
    }
}
