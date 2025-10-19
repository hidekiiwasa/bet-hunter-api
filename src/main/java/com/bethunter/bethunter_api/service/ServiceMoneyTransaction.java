package com.bethunter.bethunter_api.service;

import com.bethunter.bethunter_api.dto.moneyTransaction.MoneyTransactionRequestCreate;
import com.bethunter.bethunter_api.dto.moneyTransaction.MoneyTransactionRequestUpdate;
import com.bethunter.bethunter_api.dto.moneyTransaction.MoneyTransactionResponse;
import com.bethunter.bethunter_api.exception.AccountNotFound;
import com.bethunter.bethunter_api.mapper.MoneyTransactionMapper;
import com.bethunter.bethunter_api.model.MoneyTransaction;
import com.bethunter.bethunter_api.repository.RepositoryAccount;
import com.bethunter.bethunter_api.repository.RepositoryMoneyTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceMoneyTransaction {

    @Autowired
    private RepositoryMoneyTransaction repositoryMoneyTransaction;

    @Autowired
    private RepositoryAccount repositoryAccount;

    @Autowired
    private MoneyTransactionMapper moneyTransactionMapper;

    public MoneyTransactionResponse createMoneyTransaction(MoneyTransactionRequestCreate dto) {
        return repositoryAccount.findById(dto.id_account())
                .map(account -> {
                    MoneyTransaction moneyTransaction = moneyTransactionMapper.toEntity(dto, account);
                    return moneyTransactionMapper.toResponseDTO(repositoryMoneyTransaction.save(moneyTransaction));
                })
                .orElseThrow(() -> new AccountNotFound());
    }

    public List<MoneyTransactionResponse> findAll() {
        return repositoryMoneyTransaction.findAll().stream()
                .map(moneyTransactionMapper::toResponseDTO)
                .toList();
    }

    public Optional<MoneyTransactionResponse> findById(String id) {
        return repositoryMoneyTransaction.findById(id)
                .map(moneyTransactionMapper::toResponseDTO);
    }

    public Optional<MoneyTransactionResponse> update(String id, MoneyTransactionRequestUpdate dto) {
        return repositoryAccount.findById(dto.id_account())
                .flatMap(account -> repositoryMoneyTransaction.findById(id)
                        .map(transaction -> {
                            moneyTransactionMapper.updateEntity(transaction, dto, account);
                            MoneyTransaction updated = repositoryMoneyTransaction.save(transaction);
                            return moneyTransactionMapper.toResponseDTO(updated);
                        })
                );
    }

    public boolean delete(String id) {
        if (repositoryMoneyTransaction.existsById(id)) {
            repositoryMoneyTransaction.deleteById(id);
            return true;
        }
        return false;
    }
}
