package com.nulp.paymentservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TopUpRequest {
    private BigDecimal amount;
}
