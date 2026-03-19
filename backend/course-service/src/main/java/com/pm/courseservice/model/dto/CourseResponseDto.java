package com.pm.courseservice.model.dto;

import jakarta.validation.constraints.NotBlank;
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
public class CourseResponseDto {

    private UUID courseId;
    private String title;
    private UUID courseOwner;
    private String description;
    private double price;
    private LocalDateTime createdAt;
}
