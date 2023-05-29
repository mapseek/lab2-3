package com.nulp.paymentservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class PaymentRequest {
    private Long accountId;
    private BigDecimal amount;
}
