package com.pm.enrollmentservice.mapper;

import com.pm.enrollmentservice.model.dto.EnrollmentRequestDto;
import com.pm.enrollmentservice.model.dto.EnrollmentResponseDto;
import com.pm.enrollmentservice.model.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnrollmentMapper {

    EnrollmentResponseDto toDto(Enrollment enrollment);
    List<EnrollmentResponseDto> toDto(List<Enrollment> enrollments);
}
