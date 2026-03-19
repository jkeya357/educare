package com.pm.enrollmentservice.service.impl;

import com.pm.enrollmentservice.exception.StudentAllreadyRegisteredException;
import com.pm.enrollmentservice.mapper.EnrollmentMapper;
import com.pm.enrollmentservice.model.dto.EnrollmentRequestDto;
import com.pm.enrollmentservice.model.dto.EnrollmentResponseDto;
import com.pm.enrollmentservice.model.entity.Enrollment;
import com.pm.enrollmentservice.model.utils.Status;
import com.pm.enrollmentservice.repository.EnrollmentRepository;
import com.pm.enrollmentservice.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;

    @Override
    public List<EnrollmentResponseDto> getEnrollmentsById(UUID studentId) {
        List<Enrollment> enrollmentRes = enrollmentRepository.findByStudentId(studentId);
        return enrollmentMapper.toDto(enrollmentRes);
    }

    @Override
    public EnrollmentResponseDto createEnrollment(EnrollmentRequestDto enrollmentRequestDto) {

        boolean studentId = enrollmentRepository.existsByStudentIdAndCourseId(enrollmentRequestDto.getStudentId(), enrollmentRequestDto.getCourseId());

        if(studentId){
            throw new StudentAllreadyRegisteredException("The student is already enrolled for the specified course");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(enrollmentRequestDto.getStudentId());
        enrollment.setCourseId(enrollmentRequestDto.getCourseId());
        enrollment.setStatus(enrollmentRequestDto.getStatus() ==  null ? Status.PENDING : enrollmentRequestDto.getStatus());
        enrollment.setEnrollmentDate(enrollmentRequestDto.getEnrollmentDate() == null ? LocalDateTime.now() : enrollmentRequestDto.getEnrollmentDate());
        enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDto(enrollment);
    }
}
