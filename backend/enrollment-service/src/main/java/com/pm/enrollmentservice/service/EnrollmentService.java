package com.pm.enrollmentservice.service;

import com.pm.enrollmentservice.model.dto.EnrollmentRequestDto;
import com.pm.enrollmentservice.model.dto.EnrollmentResponseDto;

import java.util.List;
import java.util.UUID;

public interface EnrollmentService {

    List<EnrollmentResponseDto> getEnrollments();
    List<EnrollmentResponseDto> getEnrollmentsById(UUID studentId);
    EnrollmentResponseDto createEnrollment(EnrollmentRequestDto enrollmentRequestDto);
}
