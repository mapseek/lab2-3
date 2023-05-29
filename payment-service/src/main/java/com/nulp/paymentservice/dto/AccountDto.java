package com.nulp.paymentservice.dto;

import com.nulp.paymentservice.domain.Account;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
public class AccountDto {
    private Long id;
    private BigDecimal balance;
    private List<PaymentDto> payments;
    private Long creditCardId;
    private Boolean blocked;
}
