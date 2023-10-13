package com.example.airbnb.controller;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.dto.RequestAccommodation;
import com.example.airbnb.dto.RequestAccommodationImage;
import com.example.airbnb.entity.Accommodation;
import com.example.airbnb.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static com.example.airbnb.utils.ApiUtils.ApiResult;
import static com.example.airbnb.utils.ApiUtils.success;
@RestController
@RequestMapping("/api/accommodation")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService  accommodationService;

    @PostMapping
    public ApiResult<String> createAccommodation(@RequestPart RequestAccommodation requestAccommodation
            , @RequestPart List<MultipartFile> multipartFiles
            , @AuthenticationPrincipal UserDetailsImpl userDetails) {

        List<RequestAccommodationImage> imageList = new ArrayList<>();
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                // multipartFile: 이미지, byte[]로 저장
                byte[] bytes = multipartFile.getBytes();
                // 이미지 저장 주소
                ClassPathResource classPathResource = new ClassPathResource("src\\main\\resources\\images");
                // 이미지 저장 주소 + 파일 이름
                String filePath =  classPathResource.getPath() + File.separator + multipartFile.getOriginalFilename();
                // 최종 주소
                Path path = Paths.get(filePath);
                Files.createDirectories(path.getParent());

                // 파일 저장
                File newFile = new File(filePath);
                newFile.createNewFile();
                Files.write(path, bytes);
                imageList.add(new RequestAccommodationImage(path.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        accommodationService
                .accommodationSave(requestAccommodation, imageList, userDetails.getUser());
        return success("Accommodation saved successfully");
    }
}
