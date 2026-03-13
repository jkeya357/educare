package com.pm.userservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginUserRequestDto {

    @NotBlank(message = "user email is required")
    private String email;
    @NotBlank(message = "the user password is required")
    private String password;
}
