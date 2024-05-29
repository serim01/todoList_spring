package com.sparta.todolist.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@RestController
@Tag(name = "FileUploadController", description = "파일업로드 관련 api")
public class FileUploadController {
    private static final String UPLOAD_DIR = "C:/uploads/";

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName == null || !isImageFile(fileName)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미지 파일만 업로드 가능합니다.");
        }

        try {
            File dest = new File(UPLOAD_DIR + file.getOriginalFilename());
            file.transferTo(dest);
            return ResponseEntity.ok("파일 업로드 성공: " + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패: " + file.getOriginalFilename());
        }
    }

    private boolean isImageFile(String fileName) {
        String lowerCaseFileName = fileName.toLowerCase();
        return lowerCaseFileName.endsWith(".jpg") || lowerCaseFileName.endsWith(".jpeg") || lowerCaseFileName.endsWith(".png");
    }
}