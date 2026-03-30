package com.pm.courseservice.service;

import com.pm.courseservice.model.Course;
import com.pm.courseservice.model.dto.CourseResponseDto;
import com.pm.courseservice.model.dto.CreateCourseRequestDto;
import com.pm.courseservice.model.dto.UpdateCourseRequestDto;
import com.pm.courseservice.model.dto.UpdateCourseResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface CourseService {

    List<CourseResponseDto> getAllCourses();
    List<CourseResponseDto> createCourseList(List<CreateCourseRequestDto> requestDtoList);
    CourseResponseDto createCourse(CreateCourseRequestDto requestDto);
    //UpdateCourseResponseDto updateCourse(UpdateCourseRequestDto requestDto);
    void updateCourseImage(UUID courseId, String imageUrl);
}
