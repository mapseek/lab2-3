package com.skyba.paymentservice.service;

import com.skyba.paymentservice.dto.AccountDto;
import com.skyba.paymentservice.dto.CreditCardDto;

import java.util.List;

public interface CreditCardService {
    List<CreditCardDto> getAll();

    CreditCardDto getById(Long id);

    CreditCardDto createCreditCard(CreditCardDto createBody);

    CreditCardDto updateCreditCard(Long id, CreditCardDto updateBody);

    void deleteCreditCard(Long id);

}
