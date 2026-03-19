package com.pm.paymentservice.model.dto;

import com.pm.paymentservice.model.util.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDto {

    private double amount;
    private Status status;
}
