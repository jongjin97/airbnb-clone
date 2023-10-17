package com.example.airbnb.controller;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.dto.RequestAccommodation;
import com.example.airbnb.service.AccommodationService;
import com.example.airbnb.service.FileService;
import com.example.airbnb.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accommodation")
@RequiredArgsConstructor
public class AccommodationController {
    @Value("${image.upload.path}")
    private String uploadPath;
    private final FileService fileService;
    private final AccommodationService accommodationService;
    @PostMapping
    public ApiUtils.ApiResult<String> createAccommodation(@RequestBody RequestAccommodation RequestAccommodation
            , @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        System.out.println();
        for(List<String> lists : RequestAccommodation.getImageSrc()){
            String savedFileName = fileService.uploadFile(uploadPath, lists.get(0));
            lists.set(0, savedFileName);
        }
        accommodationService.createAccommodation(RequestAccommodation);
        return ApiUtils.success("success");
    }
}
