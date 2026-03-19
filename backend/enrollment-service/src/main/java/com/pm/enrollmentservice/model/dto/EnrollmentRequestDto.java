package com.pm.enrollmentservice.model.dto;

import com.pm.enrollmentservice.model.utils.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentRequestDto {

    @NotBlank(message = "the students id is required to enroll to a course")
    private UUID studentId;

    @NotBlank(message = "the course id is required")
    private UUID courseId;

    private Status status;

    private LocalDateTime enrollmentDate;
}

