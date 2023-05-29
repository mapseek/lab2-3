package com.skyba.paymentservice.service.impl;

import com.skyba.paymentservice.domain.Account;
import com.skyba.paymentservice.domain.Client;
import com.skyba.paymentservice.domain.Payment;
import com.skyba.paymentservice.dto.AccountDto;
import com.skyba.paymentservice.dto.ClientDto;
import com.skyba.paymentservice.dto.PaymentDto;
import com.skyba.paymentservice.dto.PaymentRequest;
import com.skyba.paymentservice.repository.AccountRepository;
import com.skyba.paymentservice.repository.ClientRepository;
import com.skyba.paymentservice.repository.PaymentRepository;
import com.skyba.paymentservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public List<ClientDto> getAll() {
        return clientRepository.findAll().stream().map(Client::toDto).collect(Collectors.toList());
    }

    @Override
    public ClientDto getById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(()-> new RuntimeException("Client is not found"));
        return client.toDto();
    }

    @Override
    public ClientDto createClient(ClientDto createBody) {
        Client client = createBody.toDomain();
        Client stored = clientRepository.save(client);
        return stored.toDto();
    }

    @Override
    public ClientDto updateClient(Long id, ClientDto updateBody) {
        Client persisted = clientRepository.findById(id).orElseThrow(()-> new RuntimeException("Client is not found"));

        String email = updateBody.getEmail();
        if (email != null) persisted.setEmail(email);

        String firstName = updateBody.getFirstName();
        if (firstName != null) persisted.setFirstName(firstName);

        String lastName = updateBody.getLastName();
        if (lastName != null) persisted.setLastName(lastName);

        Client stored = clientRepository.save(persisted);
        return stored.toDto();
    }

    @Override
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(()-> new RuntimeException("Client is not found"));
        clientRepository.delete(client);
    }

    @Override
    public AccountDto blockAccount(Long clientId, Long accountId) {
        Client client = clientRepository.findById(clientId).orElseThrow(()-> new RuntimeException("Client is not found"));
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account is not found"));
        if (account.getCreditCard().getClient().getId() != client.getId()) throw new RuntimeException("Wrong account for client");
        if (account.getBlocked() == true) throw new RuntimeException("Account already blocked");
        account.setBlocked(true);
        Account stored = accountRepository.save(account);
        return stored.toDto();
    }

    @Override
    public AccountDto topUpAccount(Long clientId, Long accountId, BigDecimal amount) {
        Client client = clientRepository.findById(clientId).orElseThrow(()-> new RuntimeException("Client is not found"));
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account is not found"));
        if (account.getCreditCard().getClient().getId() != client.getId()) throw new RuntimeException("Wrong account for client");
        if (account.getBlocked() == true) throw new RuntimeException("Account blocked and can't be topped up");

        account.setBalance(account.getBalance().add(amount));
        Account stored = accountRepository.save(account);
        return stored.toDto();
    }

    @Override
    public PaymentDto makePayment(Long clientId, PaymentRequest request) {
        Client client = clientRepository.findById(clientId).orElseThrow(()-> new RuntimeException("Client is not found"));
        Account account = accountRepository.findById(request.getAccountId()).orElseThrow(() -> new RuntimeException("Account is not found"));
        if (account.getCreditCard().getClient().getId() != client.getId()) throw new RuntimeException("Wrong account for client");
        if (account.getBlocked() == true) throw new RuntimeException("Account is blocked");

        BigDecimal balance = account.getBalance();
        if (balance.compareTo(request.getAmount()) < 0) throw new RuntimeException("Account balance is less than payment amount");

        account.setBalance(balance.subtract(request.getAmount()));
        Account storedAccount = accountRepository.save(account);

        Payment payment = new Payment()
                .setAccount(storedAccount)
                .setAmount(request.getAmount())
                .setDate(LocalDate.now());

        Payment stored = paymentRepository.save(payment);
        return stored.toDto();
    }

    @Override
    public List<PaymentDto> getAllPaymentsByClient(Long clientId) {
        return paymentRepository.findAllByAccountCreditCardClientId(clientId).stream()
                .map(Payment::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> getAllAccountsByClient(Long clientId) {
        return accountRepository.findAllByCreditCardClientId(clientId).stream()
                .map(Account::toDto)
                .collect(Collectors.toList());
    }
}
