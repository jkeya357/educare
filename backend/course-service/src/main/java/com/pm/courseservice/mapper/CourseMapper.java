package com.pm.courseservice.mapper;

import com.pm.courseservice.model.Course;
import com.pm.courseservice.model.dto.CourseResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {

    List<CourseResponseDto> toDto(List<Course> courses);

    CourseResponseDto toDto(Course course);
}
