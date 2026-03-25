package com.pm.courseservice.controller;

import com.pm.courseservice.model.Course;
import com.pm.courseservice.service.CourseService;
import com.pm.courseservice.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course/s3")
public class S3Controller {

    private final S3Service s3Service;
    private final CourseService courseService;

    @PostMapping("/upload/{courseId}")
    public ResponseEntity<String> upload(
            @PathVariable UUID courseId,
            @RequestParam("file") MultipartFile file
    ) {

        String key = UUID.randomUUID() + file.getOriginalFilename();
        String url = s3Service.uploadFile(file, key);
        System.out.println("image url" + url);
        courseService.updateCourseImage(courseId, url);
        return ResponseEntity.ok(url);
    }
}
