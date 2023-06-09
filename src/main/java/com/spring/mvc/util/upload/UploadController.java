package com.spring.mvc.util.upload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/*
    실제 파일은 외부에 저장하고,
    DB 에는 파일의 경로를 저장
 */
@Controller
@Slf4j
public class UploadController {

    // 첨부파일 저장 루트경로 - 설정 파일에 경로 작성하고 @Value 로 주입받기(properties)
    @Value("${file.upload.root-path}")
    private String rootPath;

    @GetMapping("/upload-form")
    public String uploadForm() {
        return "upload/upload-form";
    }

    @PostMapping("/upload-form")
    public String uploadForm(@RequestParam("thumbnail") MultipartFile file) { // 다양한 종류의 파일을 요청 받을 수 있음 (10MB 제한)
        log.info("file-name: {}", file.getOriginalFilename());
        log.info("file-size: {}KB", (double)file.getSize() / 1024);
        log.info("file-type: {}", file.getContentType());

        // 첨부파일을 스토리지에 저장
        // 1. 루트 디렉토리 생성 -> 루트 폴더가 없을 시 생성하도록 하는 코드 필수!
        File root = new File(rootPath);
        if (!root.exists()) root.mkdirs(); // 경로에 있는 모든 폴더 생성

        // 2. 파일을 해당 경로에 저장
//        File uploadFile = new File(rootPath, file.getOriginalFilename());
//
//        try {
//            file.transferTo(uploadFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        uploadFile 메서드로 리팩터링

        String filePath = FileUtil.uploadFile(file, rootPath);

        return "redirect:/upload-form";
    }
}
