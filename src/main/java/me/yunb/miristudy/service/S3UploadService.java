package me.yunb.miristudy.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import me.yunb.miristudy.config.S3Config;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3UploadService {

    private final S3Config s3Config;

    private final AmazonS3 amazonS3;

    // {base-path}/{uuid}/{fileName}.xxx
    // s3로 업로드 후, 객체 url 리턴
    public String upload(MultipartFile file) throws IOException {
        // 실무에서는 file 이름 띄어쓰기 및 특수문자 처리해야 함. ex> encoding

        // create key
        String uuid = UUID.randomUUID().toString();
        Path basePath = s3Config.getBasePath();
        String key = basePath.resolve(uuid).resolve(file.getOriginalFilename()).toString();

        // create request
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                s3Config.getBucketName(),
                key,
                file.getInputStream(),
                makeMetaData(file))
                .withCannedAcl(CannedAccessControlList.PublicRead); // 이거 해야 업로드한 객체 외부에서 사용 가능

        // upload
        amazonS3.putObject(putObjectRequest);

        return amazonS3.getUrl(s3Config.getBucketName(), key).toString();
    }

    private ObjectMetadata makeMetaData(MultipartFile multipartFile) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());
        return objectMetadata;
    }

}
