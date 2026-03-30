package com.pm.courseservice.service.impl;

import com.pm.courseservice.mapper.CourseMapper;
import com.pm.courseservice.model.Course;
import com.pm.courseservice.model.dto.CourseResponseDto;
import com.pm.courseservice.model.dto.CreateCourseRequestDto;
import com.pm.courseservice.model.dto.UpdateCourseRequestDto;
import com.pm.courseservice.model.dto.UpdateCourseResponseDto;
import com.pm.courseservice.repository.CourseRepository;
import com.pm.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    @Cacheable(value = "COURSE_CACHE")
    public List<CourseResponseDto> getAllCourses() {

            List<Course> course =  courseRepository.findAll();
            return courseMapper.toDto(course);
    }

    @Override
    @CacheEvict(value = "COURSE_CACHE", allEntries = true)
    public List<CourseResponseDto> createCourseList(List<CreateCourseRequestDto> requestDtoList) {
        List<Course> courses = requestDtoList.stream()
                .map(req -> Course.builder()
                        .title(req.getTitle())
                        .courseOwner(req.getCourseOwner())
                        .description(req.getDescription())
                        .price(req.getPrice())
                        .imageUrl(req.getImageUrl() == null ? "" : req.getImageUrl())
                        .createdAt(req.getCreatedAt() == null ?LocalDateTime.now() : req.getCreatedAt())
                        .build()
                ).toList();

        List<Course> savedCourse = courseRepository.saveAll(courses);
        return courseMapper.toDto(savedCourse);
    }

    @Override
    @CachePut(value = "COURSE_CACHE", key = "#result.courseId()")
    @CacheEvict(value = "COURSE_CACHE", allEntries = true)
    public CourseResponseDto createCourse(CreateCourseRequestDto requestDto){

        Course course = new Course();
        course.setTitle(requestDto.getTitle());
        course.setCourseOwner(requestDto.getCourseOwner());
        course.setDescription(requestDto.getDescription());
        course.setPrice(requestDto.getPrice());
        course.setImageUrl(requestDto.getImageUrl() == null ? "" : requestDto.getImageUrl());
        course.setCreatedAt(requestDto.getCreatedAt() == null ? LocalDateTime.now() : requestDto.getCreatedAt());
        courseRepository.save(course);

        return courseMapper.toDto(course);
    }

//    @Override
//    public UpdateCourseResponseDto updateCourse(UpdateCourseRequestDto requestDto) {
//
//        Course findCourse = new Course();
//    }

    @Override
    public void updateCourseImage(UUID courseId, String imageUrl) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()->new RuntimeException("Course not found"));
        course.setImageUrl(imageUrl);
        courseRepository.save(course);
    }
}
