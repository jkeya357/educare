package com.pm.paymentservice.service;

import com.pm.paymentservice.model.dto.CreatePaymentRequestDto;
import com.pm.paymentservice.model.dto.PaymentResponseDto;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    List<PaymentResponseDto> getPayments(UUID userId);
    PaymentResponseDto createPayment(CreatePaymentRequestDto createPaymentRequestDto);
}
