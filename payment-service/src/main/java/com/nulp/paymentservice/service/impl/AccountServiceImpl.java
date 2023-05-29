package com.skyba.paymentservice.service.impl;

import com.skyba.paymentservice.domain.Account;
import com.skyba.paymentservice.domain.CreditCard;
import com.skyba.paymentservice.dto.AccountDto;
import com.skyba.paymentservice.repository.AccountRepository;
import com.skyba.paymentservice.repository.CreditCardRepository;
import com.skyba.paymentservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CreditCardRepository creditCardRepository;

    @Override
    public List<AccountDto> getAll() {
        return accountRepository.findAll().stream().map(Account::toDto).collect(Collectors.toList());
    }

    @Override
    public AccountDto getById(Long id) {
        return accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account is not found")).toDto();
    }

    @Override
    public AccountDto createAccount(AccountDto createBody) {
        CreditCard creditCard = creditCardRepository.findById(createBody.getCreditCardId()).orElseThrow(()-> new RuntimeException("Credit card is not found"));
        Account account = new Account()
                .setBalance(BigDecimal.ZERO)
                .setBlocked(false)
                .setCreditCard(creditCard);

        Account stored = accountRepository.save(account);
        return stored.toDto();
    }

    @Override
    public AccountDto updateAccount(Long id, AccountDto updateBody) {
        Account persisted = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account is not found"));
        Boolean blocked = updateBody.getBlocked();
        if (blocked!= null) persisted.setBlocked(blocked);

        Account stored = accountRepository.save(persisted);
        return stored.toDto();
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account is not found"));
        accountRepository.delete(account);
    }
}
