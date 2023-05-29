package com.nulp.paymentservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class PaymentDto {
    private Long id;
    private BigDecimal amount;
    private LocalDate date;
    private Long accountId;
}
