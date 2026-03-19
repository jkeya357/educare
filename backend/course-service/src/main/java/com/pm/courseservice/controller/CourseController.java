package com.pm.courseservice.controller;

import com.pm.courseservice.mapper.CourseMapper;
import com.pm.courseservice.model.Course;
import com.pm.courseservice.model.dto.CourseResponseDto;
import com.pm.courseservice.model.dto.CreateCourseRequestDto;
import com.pm.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> findAll(){
        List<Course> res = courseService.getAllCourses();
        List<CourseResponseDto> dto = courseMapper.toDto(res);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(@RequestBody CreateCourseRequestDto requestDto){

        Course res = courseService.createCourse(requestDto);
        CourseResponseDto dto = courseMapper.toDto(res);
        return ResponseEntity.ok().body(dto);
    }
}
