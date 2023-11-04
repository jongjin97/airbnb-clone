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
//@Api(tags = "Review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/{id}")
//    @ApiImplicitParams(value = {
//             @ApiImplicitParam(name = "id", value = "게시글 id", required = true, dataType = "String")
//            ,@ApiImplicitParam(name = "requestReview", value = "리뷰 정보", required = true, dataType = "RequestReview")
//    })
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "리뷰 저장 성공")
//            ,@ApiResponse(code = 400, message = "리뷰 저장 실패")
//            ,@ApiResponse(code = 401,  message = "로그인 필요")
//            ,@ApiResponse(code = 403, message = "권한 없음")
//    })
//    @ApiOperation( value = "리뷰 저장", notes = "리뷰 저장")
    public ApiResult<String> saveReview(@PathVariable String id, @RequestBody RequestReview requestReview
            , @AuthenticationPrincipal UserDetailsImpl userDetails){
        reviewService.saveReview(id, requestReview, userDetails.getUser());

        return success("success");
    }
}
