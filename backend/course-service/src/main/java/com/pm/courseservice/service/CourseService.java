package com.pm.courseservice.service;

import com.pm.courseservice.model.Course;
import com.pm.courseservice.model.dto.CourseResponseDto;
import com.pm.courseservice.model.dto.CreateCourseRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface CourseService {

    List<Course> getAllCourses();
    List<CourseResponseDto> createCourseList(List<CreateCourseRequestDto> requestDtoList);
    CourseResponseDto createCourse(CreateCourseRequestDto requestDto);
    void updateCourseImage(UUID courseId, String imageUrl);
}
