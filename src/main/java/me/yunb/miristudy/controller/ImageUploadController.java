package me.yunb.miristudy.controller;

import lombok.RequiredArgsConstructor;
import me.yunb.miristudy.service.S3UploadService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageUploadController {

    private final S3UploadService uploadService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(MultipartFile image) throws IOException {
        return uploadService.upload(image);
    }

}
