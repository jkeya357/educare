package com.pm.paymentservice.service.impl;

import com.pm.paymentservice.mapper.PaymentMapper;
import com.pm.paymentservice.model.Payment;
import com.pm.paymentservice.model.dto.CreatePaymentRequestDto;
import com.pm.paymentservice.model.dto.PaymentResponseDto;
import com.pm.paymentservice.model.util.Status;
import com.pm.paymentservice.repository.PaymentRepository;
import com.pm.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;


    @Override
    public List<PaymentResponseDto> getPayments(UUID userId) {
        List<Payment> payments = paymentRepository.findByUserId(userId);
        return paymentMapper.toDto(payments);
    }

    @Override
    public PaymentResponseDto createPayment(CreatePaymentRequestDto paymentRequestDto) {

        Payment payment = new Payment();
        payment.setUserId(paymentRequestDto.getUserId());
        payment.setAmount(paymentRequestDto.getAmount());
        payment.setStatus(paymentRequestDto.getStatus()  == null ? Status.COMPLETED : paymentRequestDto.getStatus());
        paymentRepository.save(payment);

        return paymentMapper.toDto(payment);
    }
}
