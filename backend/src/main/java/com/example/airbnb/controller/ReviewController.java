package com.example.airbnb.controller;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.dto.RequestReview;
import com.example.airbnb.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.example.airbnb.utils.ApiUtils.ApiResult;
import static com.example.airbnb.utils.ApiUtils.success;
@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/{id}")
    public ApiResult<String> saveReview(@PathVariable String id, @RequestBody RequestReview requestReview
            , @AuthenticationPrincipal UserDetailsImpl userDetails){
        reviewService.saveReview(id, requestReview, userDetails.getUser());

        return success("success");
    }
}
