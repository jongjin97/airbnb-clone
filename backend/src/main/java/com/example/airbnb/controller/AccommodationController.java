package com.example.airbnb.controller;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.document.User;
import com.example.airbnb.dto.RequestAccommodation;
import com.example.airbnb.dto.ResponseAccommodation;
import com.example.airbnb.service.AccommodationService;
import com.example.airbnb.service.FileService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.example.airbnb.utils.ApiUtils.ApiResult;
import static com.example.airbnb.utils.ApiUtils.success;
@RestController
@RequestMapping("/api/accommodation")
@RequiredArgsConstructor
//@Api(tags = "Accommodation")
public class AccommodationController {
    @Value("${image.upload.path}")
    private String uploadPath;
    private final FileService fileService;
    private final AccommodationService accommodationService;
    @PostMapping
//    @ApiImplicitParams(value = {
//             @ApiImplicitParam(name = "RequestAccommodation", value = "숙소 정보", required = true, dataType = "RequestAccommodation")
//            ,@ApiImplicitParam(name = "userDetails", value = "유저 정보", required = true, dataType = "UserDetailsImpl")
//    })
//    @ApiResponses(value = {
//             @ApiResponse(code = 200, message = "숙소 생성 성공")
//            ,@ApiResponse(code = 400, message = "숙소 생성 실패")
//    })
//    @ApiOperation(value = "숙소 생성", notes = "숙소 생성")
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
    @GetMapping
//    @ApiImplicitParam( name = "userDetails", value = "유저 정보", required = true, dataType = "UserDetailsImpl")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "숙소 조회 성공")
//            ,@ApiResponse(code = 400, message = "숙소 조회 실패")
//    })
//    @ApiOperation(value = "숙소 조회", notes = "숙소 조회")
    public ApiResult<List<ResponseAccommodation>> getAccommodationList(@AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        User user = userDetails.getUser();
        List<ResponseAccommodation> accommodationList = user.getAccommodation().stream()
                .map(accommodation -> {
                    try {
                        ResponseAccommodation responseAccommodation = new ResponseAccommodation(accommodation);
                        responseAccommodation.setImageByte(fileService.downloadFile(accommodation.getImageSrc()));
                        return responseAccommodation;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
        return success(accommodationList);
    }
    @GetMapping("/lists")
//    @ApiImplicitParam( name = "requestParam", value = "요청 파라미터", required = true, dataType = "Map<String, String>")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "숙소 조회 성공")
//            ,@ApiResponse(code = 400, message = "숙소 조회 실패")
//    })
//    @ApiOperation(value = "숙소 조회", notes = "숙소 조회")
    public ApiResult<List<ResponseAccommodation>> getAccommodationList(@RequestParam Map<String, String> requestParam) throws Exception {
        List<ResponseAccommodation> accommodationList = accommodationService.findAllByParam(requestParam);
        return success(accommodationList);
    }

    @GetMapping("/{id}")
//    @ApiImplicitParam( name = "id", value = "숙소 id", required = true, dataType = "String")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "숙소 조회 성공")
//            ,@ApiResponse(code = 400, message = "숙소 조회 실패")
//    })
//    @ApiOperation(value = "숙소 조회", notes = "숙소 조회")
    public ApiResult<ResponseAccommodation> getAccommodation(@PathVariable String id) throws Exception {
        ResponseAccommodation responseAccommodation = accommodationService.findById(id);
        return success(responseAccommodation);
    }
    @DeleteMapping("/{id}")
//    @ApiImplicitParam( name = "id", value = "숙소 id", required = true, dataType = "String")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "숙소 삭제 성공")
//            ,@ApiResponse(code = 400, message = "숙소 삭제 실패")
//    })
//    @ApiOperation(value = "숙소 삭제", notes = "숙소 삭제")
    public ApiResult<String> deleteAccommodation(@PathVariable String id) throws Exception {
        accommodationService.deleteAccommodation(id);
        return success("success");
    }
}
