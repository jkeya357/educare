package com.pm.courseservice.service.impl;

import com.pm.courseservice.model.Course;
import com.pm.courseservice.model.dto.CreateCourseRequestDto;
import com.pm.courseservice.repository.CourseRepository;
import com.pm.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course createCourse(CreateCourseRequestDto requestDto) {

        Course course = new Course();
        course.setTitle(requestDto.getTitle());
        course.setCourseOwner(requestDto.getCourseOwner());
        course.setDescription(requestDto.getDescription());
        course.setPrice(requestDto.getPrice());
        course.setCreatedAt(requestDto.getCreatedAt() == null ? LocalDateTime.now() : requestDto.getCreatedAt());
        return courseRepository.save(course);
    }
}
