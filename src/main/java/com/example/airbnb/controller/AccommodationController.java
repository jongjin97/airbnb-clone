package com.example.airbnb.controller;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.document.Accommodation;
import com.example.airbnb.dto.RequestAccommodation;
import com.example.airbnb.dto.ResponseAccommodation;
import com.example.airbnb.service.AccommodationService;
import com.example.airbnb.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.example.airbnb.utils.ApiUtils.ApiResult;
import static com.example.airbnb.utils.ApiUtils.success;
@RestController
@RequestMapping("/api/accommodation")
@RequiredArgsConstructor
public class AccommodationController {
    @Value("${image.upload.path}")
    private String uploadPath;
    private final FileService fileService;
    private final AccommodationService accommodationService;
    @PostMapping
    public ApiResult<String> createAccommodation(@RequestBody RequestAccommodation RequestAccommodation
            , @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        System.out.println();
        for(List<String> lists : RequestAccommodation.getImageSrc()){
            String savedFileName = fileService.uploadFile(uploadPath, lists.get(0));
            lists.set(0, savedFileName);
        }
        accommodationService.createAccommodation(RequestAccommodation, userDetails.getUser());
        return success("success");
    }

    @GetMapping("/lists")
    public ApiResult<List<ResponseAccommodation>> getAccommodationList(@AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        List<ResponseAccommodation> accommodationList = accommodationService.findAllAccommodation();
        return success(accommodationList);
    }
}
