package com.skyba.paymentservice.service.impl;

import com.skyba.paymentservice.domain.Account;
import com.skyba.paymentservice.domain.Payment;
import com.skyba.paymentservice.dto.PaymentDto;
import com.skyba.paymentservice.repository.AccountRepository;
import com.skyba.paymentservice.repository.PaymentRepository;
import com.skyba.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<PaymentDto> getAll() {
        return paymentRepository.findAll().stream().map(Payment::toDto).collect(Collectors.toList());
    }

    @Override
    public PaymentDto getById(Long id) {
        return paymentRepository.findById(id).orElseThrow(()-> new RuntimeException("Payment is not found")).toDto();
    }

    @Override
    public PaymentDto makePayment(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account is not found"));
        BigDecimal balance = account.getBalance();
        if (balance.compareTo(amount) < 0) throw new RuntimeException("Account balance is less than payment amount");

        account.setBalance(balance.subtract(amount));
        Account storedAccount = accountRepository.save(account);
        Payment payment = new Payment()
                .setAccount(storedAccount)
                .setAmount(amount)
                .setDate(LocalDate.now());

        Payment stored = paymentRepository.save(payment);
        return stored.toDto();
    }

    @Override
    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment is not found"));
        paymentRepository.delete(payment);
    }
}
