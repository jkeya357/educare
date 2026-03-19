package com.pm.courseservice.service;

import com.pm.courseservice.model.Course;
import com.pm.courseservice.model.dto.CreateCourseRequestDto;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();
    Course createCourse(CreateCourseRequestDto requestDto);
}
