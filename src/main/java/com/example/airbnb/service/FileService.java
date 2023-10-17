package com.example.airbnb.service;


import lombok.extern.java.Log;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.UUID;

@Service
@Log
public class FileService {

    public String uploadFile(String uploadPath, String fileData) throws Exception {
        UUID uuid = UUID.randomUUID();
        String[] imageDataArray = fileData.split(",");
        String imageType = imageDataArray[0].split("/")[1].split(";")[0];
        byte[] imageBytes = Base64.getDecoder().decode(imageDataArray[1]);

        String fileUploadFullUrl = uploadPath + "/" + uuid.toString() + "." + imageType;
        File directory = new File(uploadPath);
        if(!directory.exists()) {
            directory.mkdirs();
            log.info("폴더를 생성하였습니다.");
        }
        File newFile = new File(fileUploadFullUrl);
        FileUtils.writeByteArrayToFile(newFile, imageBytes);

        return fileUploadFullUrl;
    }

    public void deleteFile(String filePath) throws Exception {

        File deleteFile = new File(filePath);

        if(deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }

}
