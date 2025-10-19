package com.bethunter.bethunter_api.controller;

import com.bethunter.bethunter_api.dto.moneyTransaction.MoneyTransactionRequestCreate;
import com.bethunter.bethunter_api.dto.moneyTransaction.MoneyTransactionRequestUpdate;
import com.bethunter.bethunter_api.dto.moneyTransaction.MoneyTransactionResponse;
import com.bethunter.bethunter_api.service.ServiceMoneyTransaction;
import com.bethunter.bethunter_api.exception.AccountNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("money_transactions")
public class ControllerMoneyTransaction {

    @Autowired
    private ServiceMoneyTransaction serviceMoneyTransaction;

    @PostMapping
    public ResponseEntity<MoneyTransactionResponse> createMoneyTransaction(@RequestBody MoneyTransactionRequestCreate dto) {
        return ResponseEntity.status(201).body(serviceMoneyTransaction.createMoneyTransaction(dto));
    }

    @GetMapping
    public ResponseEntity<List<MoneyTransactionResponse>> findAll() {
        return ResponseEntity.ok(serviceMoneyTransaction.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<MoneyTransactionResponse> findById(@PathVariable String id) {
        return serviceMoneyTransaction.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new AccountNotFound());
    }

    @PutMapping("{id}")
    public ResponseEntity<MoneyTransactionResponse> updateMoneyTransaction(@PathVariable String id, @RequestBody MoneyTransactionRequestUpdate dto) {
        return serviceMoneyTransaction.update(id, dto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new AccountNotFound());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean result = serviceMoneyTransaction.delete(id);
        if (result) {
            return ResponseEntity.noContent().build();
        } else {
            throw new AccountNotFound();
        }
    }
}
