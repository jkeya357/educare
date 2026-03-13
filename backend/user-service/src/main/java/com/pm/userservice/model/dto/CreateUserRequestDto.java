package com.pm.userservice.model.dto;

import com.pm.userservice.model.utils.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequestDto {

    @NotBlank(message = "username cannot be empty")
    private String username;

    @NotNull(message = "password for the user is required")
    @Size(min = 8, message = "password must be at least {min}")
    private String password;

    @NotBlank(message = "email field cannot be empty")
    private String email;

    @NotBlank(message = "first name is required")
    private String firstname;

    @NotBlank(message = "last name is required")
    private String lastname;

    private Role role;

    private LocalDateTime createdDate;
}
