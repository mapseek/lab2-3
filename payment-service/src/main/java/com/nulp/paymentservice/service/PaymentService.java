package com.skyba.paymentservice.service;

import com.skyba.paymentservice.dto.PaymentDto;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    List<PaymentDto> getAll();
    PaymentDto getById(Long id);
    PaymentDto makePayment(Long accountId, BigDecimal amount);
    void deletePayment(Long id);

}
