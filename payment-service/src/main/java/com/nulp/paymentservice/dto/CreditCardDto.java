package com.nulp.paymentservice.dto;

import com.nulp.paymentservice.domain.CreditCard;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class CreditCardDto {
    private Long id;
    private String number;
    private LocalDate expiryDate;
    private String cvv;
    private Long clientId;

    public CreditCard toDomain() {
        return new CreditCard()
                .setNumber(number)
                .setCvv(cvv)
                .setExpiryDate(expiryDate);
    }
}
