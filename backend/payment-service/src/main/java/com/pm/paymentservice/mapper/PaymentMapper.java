package com.pm.paymentservice.mapper;

import com.pm.paymentservice.model.Payment;
import com.pm.paymentservice.model.dto.CreatePaymentRequestDto;
import com.pm.paymentservice.model.dto.PaymentResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

    List<PaymentResponseDto> toDto(List<Payment> payments);
    PaymentResponseDto toDto(Payment payment);
}
