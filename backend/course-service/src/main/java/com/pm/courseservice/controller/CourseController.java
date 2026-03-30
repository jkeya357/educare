package com.pm.courseservice.controller;

import com.pm.courseservice.mapper.CourseMapper;
import com.pm.courseservice.model.Course;
import com.pm.courseservice.model.dto.CourseResponseDto;
import com.pm.courseservice.model.dto.CreateCourseRequestDto;
import com.pm.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;



    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> findAll(){
        List<CourseResponseDto> res = courseService.getAllCourses();
        return ResponseEntity.ok().body(res);
    }

    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(
            @RequestBody CreateCourseRequestDto requestDto
            ) {

        CourseResponseDto dto = courseService.createCourse(requestDto);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/list")
    public ResponseEntity<List<CourseResponseDto>> createCourseList(@RequestBody List<CreateCourseRequestDto> requestDtoList){
        List<CourseResponseDto> courseResponseDtos = courseService.createCourseList(requestDtoList);
        return ResponseEntity.ok().body(courseResponseDtos);
    }
}
