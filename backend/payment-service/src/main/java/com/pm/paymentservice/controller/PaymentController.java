package com.pm.paymentservice.controller;

import com.pm.paymentservice.mapper.PaymentMapper;
import com.pm.paymentservice.model.dto.CreatePaymentRequestDto;
import com.pm.paymentservice.model.dto.PaymentResponseDto;
import com.pm.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> getPayments(@RequestParam UUID userId){

        List<PaymentResponseDto> dto = paymentService.getPayments(userId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody CreatePaymentRequestDto requestDto){
        PaymentResponseDto dto = paymentService.createPayment(requestDto);
        return ResponseEntity.ok(dto);
    }
}
