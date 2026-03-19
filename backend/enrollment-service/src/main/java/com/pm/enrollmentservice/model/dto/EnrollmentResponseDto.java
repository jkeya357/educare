package com.pm.enrollmentservice.model.dto;

import com.pm.enrollmentservice.model.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentResponseDto {

    private Status status;

    private LocalDateTime enrollmentDate;
}
