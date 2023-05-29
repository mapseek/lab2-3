package com.nulp.paymentservice.controller;

import com.nulp.paymentservice.dto.AccountDto;
import com.nulp.paymentservice.dto.AdminDto;
import com.nulp.paymentservice.dto.ClientDto;
import com.nulp.paymentservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admins")
public class AdminController {

    private final AdminService service;

    @GetMapping
    public List<AdminDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AdminDto getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public AdminDto createAdmin(@RequestBody AdminDto createBody){
        return service.createAdmin(createBody);
    }

    @PatchMapping("/{id}")
    public AdminDto updateAdmin(@PathVariable Long id,@RequestBody AdminDto updateBody){
        return service.updateAdmin(id,updateBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        service.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/accounts/{accountId}/unblock")
    public String unblockAccount(@PathVariable Long accountId) {
        service.unblockAccount(accountId);
        return String.format("account with id - {%d} was unblocked", accountId);
    }

}
