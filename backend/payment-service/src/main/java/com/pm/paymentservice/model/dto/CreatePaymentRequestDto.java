package com.pm.paymentservice.model.dto;

import com.pm.paymentservice.model.util.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePaymentRequestDto {

    @NotBlank(message = "user id required to process payment")
    private UUID userId;
    @NotBlank(message = "payment amount is required")
    @Size(min = 1, message = "amount must be a number")
    private double amount;
    private Status status;
}
