package me.yunb.miristudy.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.nio.file.Path;

@Configuration
public class S3Config {

    @Getter
    @Value("${s3.bucket.name}")
    private String bucketName;

    @Getter
    @Value("${s3.bucket.base-path}")
    private Path basePath;

    @Bean
    @Primary
    public AmazonS3 amazonS3Client() {
        return AmazonS3ClientBuilder.standard().build();
    }

}
