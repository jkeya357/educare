package com.pm.courseservice.model.dto;

import jakarta.persistence.Column;
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
public class CreateCourseRequestDto {

    @NotBlank(message = "Course title required")
    private String title;
    @NotBlank(message = "course owner is required")
    private UUID courseOwner;
    @NotBlank(message = "course description is required")
    private String description;
    @NotBlank(message = "course must contain price")
    private double price;
    private LocalDateTime createdAt;
}
