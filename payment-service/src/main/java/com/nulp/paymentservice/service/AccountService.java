package com.skyba.paymentservice.service;

import com.skyba.paymentservice.dto.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAll();

    AccountDto getById(Long id);

    AccountDto createAccount(AccountDto createBody);

    AccountDto updateAccount(Long id, AccountDto updateBody);

    void deleteAccount(Long id);
}
