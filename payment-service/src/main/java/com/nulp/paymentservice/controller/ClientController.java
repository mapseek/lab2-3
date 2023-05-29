package com.nulp.paymentservice.controller;

import com.nulp.paymentservice.dto.*;
import com.nulp.paymentservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService service;

    @GetMapping
    public List<ClientDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ClientDto getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public ClientDto createClient(@RequestBody ClientDto createBody){
        return service.createClient(createBody);
    }

    @PatchMapping("/{id}")
    public ClientDto updateClient(@PathVariable Long id,@RequestBody ClientDto updateBody){
        return service.updateClient(id,updateBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        service.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{clientId}/accounts/")
    public List<AccountDto> getAllAccountsByClient(@PathVariable Long clientId){
        return service.getAllAccountsByClient(clientId);
    }

    @PatchMapping("/{clientId}/accounts/{accountId}/block")
    public AccountDto blockAccount(@PathVariable Long clientId, @PathVariable Long accountId) {
        return service.blockAccount(clientId, accountId);
    }

    @PatchMapping("/{clientId}/accounts/{accountId}/top-up")
    public AccountDto topUpAccount(@PathVariable Long clientId, @PathVariable Long accountId, @RequestBody TopUpRequest request) {
        return service.topUpAccount(clientId, accountId, request.getAmount());
    }

    @GetMapping("/{clientId}/payments")
    public List<PaymentDto> getAllPaymentsByClient(@PathVariable Long clientId){
        return service.getAllPaymentsByClient(clientId);
    }

    @PostMapping("/{clientId}/payments")
    public PaymentDto makePayment(@PathVariable Long clientId, @RequestBody PaymentRequest payment) {
        return service.makePayment(clientId, payment);
    }
}
