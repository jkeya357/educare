package com.pm.userservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDto {

    private String token;
    private int duration;
    private UUID userId;
    private String username;
}
