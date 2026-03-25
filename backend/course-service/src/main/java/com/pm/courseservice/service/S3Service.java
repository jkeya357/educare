package com.pm.courseservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.bucketName}")
    private  String bucketName;

    public String uploadFile(MultipartFile file, String key){

        if(file.isEmpty()){
            throw new RuntimeException("File is empty");
        }

        try {
            PutObjectRequest req = PutObjectRequest
                    .builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();
            s3Client.putObject(
                    req,
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );

            return s3Client.utilities()
                    .getUrl(builder-> builder.bucket(bucketName).key(key))
                    .toExternalForm();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error message: " + e.getMessage());
            throw new RuntimeException("File upload failed!");
        }
    }
}
