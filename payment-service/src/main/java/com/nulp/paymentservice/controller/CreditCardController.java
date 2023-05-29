package com.nulp.paymentservice.controller;

import com.nulp.paymentservice.dto.AccountDto;
import com.nulp.paymentservice.dto.CreditCardDto;
import com.nulp.paymentservice.service.AccountService;
import com.nulp.paymentservice.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class CreditCardController {

    private final CreditCardService service;

    @GetMapping
    public List<CreditCardDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CreditCardDto getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public CreditCardDto createCreditCard(@RequestBody CreditCardDto createBody){
        return service.createCreditCard(createBody);
    }

    @PatchMapping("/{id}")
    public CreditCardDto updateCreditCard(@PathVariable Long id,@RequestBody CreditCardDto updateBody){
        return service.updateCreditCard(id,updateBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable Long id){
        service.deleteCreditCard(id);
        return ResponseEntity.noContent().build();
    }
}
