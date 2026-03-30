package com.pm.courseservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateCourseRequestDto {

    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private LocalDateTime updatedAt;
}
