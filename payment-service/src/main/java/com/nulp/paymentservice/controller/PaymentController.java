package com.nulp.paymentservice.controller;

import com.skyba.paymentservice.dto.AdminDto;
import com.skyba.paymentservice.dto.PaymentDto;
import com.skyba.paymentservice.dto.PaymentRequest;
import com.skyba.paymentservice.service.AdminService;
import com.skyba.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payments")
public class PaymentController {

    private final PaymentService service;

    @GetMapping
    public List<PaymentDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PaymentDto getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public PaymentDto makePayment(@RequestBody PaymentRequest paymentRequest) {
        return service.makePayment(paymentRequest.getAccountId(), paymentRequest.getAmount());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id){
        service.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
