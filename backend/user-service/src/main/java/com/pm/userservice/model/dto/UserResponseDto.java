package com.pm.userservice.model.dto;

import com.pm.userservice.model.utils.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponseDto {

    private String username;

    private String email;

    private String firstname;

    private String lastname;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdDate;

}
