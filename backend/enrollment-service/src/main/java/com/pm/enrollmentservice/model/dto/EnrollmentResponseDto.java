package com.pm.enrollmentservice.model.dto;

import com.pm.enrollmentservice.model.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentResponseDto {

    private UUID enrollmentId;

    private UUID studentId;

    private UUID courseId;

    private Status status;

    private LocalDateTime enrollmentDate;
}
