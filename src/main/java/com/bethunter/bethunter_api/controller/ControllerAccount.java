package com.bethunter.bethunter_api.controller;

import com.bethunter.bethunter_api.dto.account.AccountRequestCreate;
import com.bethunter.bethunter_api.dto.account.AccountRequestUpdate;
import com.bethunter.bethunter_api.dto.account.AccountResponse;
import com.bethunter.bethunter_api.service.ServiceAccount;
import com.bethunter.bethunter_api.exception.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accounts")
public class ControllerAccount {

    @Autowired
    private ServiceAccount serviceAccount;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequestCreate dto) {
        return ResponseEntity.status(201).body(serviceAccount.createAccount(dto));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAll() {
        return ResponseEntity.ok(serviceAccount.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountResponse> findById(@PathVariable String id) {
        return serviceAccount.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFound());
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable String id, @RequestBody AccountRequestUpdate dto) {
        return serviceAccount.update(id, dto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFound());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        boolean result = serviceAccount.delete(id);
        if (result) {
            return ResponseEntity.noContent().build();
        } else {
            throw new UserNotFound();
        }
    }
}
