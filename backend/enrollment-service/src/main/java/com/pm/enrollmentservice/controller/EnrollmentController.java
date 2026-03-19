package com.pm.enrollmentservice.controller;

import com.pm.enrollmentservice.model.dto.EnrollmentRequestDto;
import com.pm.enrollmentservice.model.dto.EnrollmentResponseDto;
import com.pm.enrollmentservice.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/enroll")
@RestController
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDto>> getAllEnrollments(
            @RequestParam UUID studentId
            ) {
        List<EnrollmentResponseDto> res = enrollmentService.getEnrollmentsById(studentId);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDto> createEnrollment(@RequestBody EnrollmentRequestDto requestDto){
        EnrollmentResponseDto res = enrollmentService.createEnrollment(requestDto);
        return ResponseEntity.ok(res);
    }
}
