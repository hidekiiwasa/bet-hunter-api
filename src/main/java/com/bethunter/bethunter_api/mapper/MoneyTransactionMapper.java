package com.bethunter.bethunter_api.mapper;

import com.bethunter.bethunter_api.dto.moneyTransaction.*;
import com.bethunter.bethunter_api.model.MoneyTransaction;
import com.bethunter.bethunter_api.model.Account;
import org.springframework.stereotype.Component;

@Component
public class MoneyTransactionMapper {

    public MoneyTransaction toEntity(MoneyTransactionRequestCreate dto, Account account) {
        if (dto == null || account == null) return null;

        MoneyTransaction transaction = new MoneyTransaction();
        transaction.setAmount(dto.amount());
        transaction.setExpensiveType(dto.expensive_type());
        transaction.setDescription(dto.description());
        transaction.setAccount(account);
        return transaction;
    }

    public MoneyTransaction toEntity(MoneyTransactionRequestUpdate dto, Account account) {
        if (dto == null || account == null) return null;

        MoneyTransaction transaction = new MoneyTransaction();
        transaction.setAmount(dto.amount());
        transaction.setExpensiveType(dto.expensive_type());
        transaction.setDescription(dto.description());
        transaction.setCreatedAt(dto.created_at());
        transaction.setAccount(account);
        return transaction;
    }

    public void updateEntity(MoneyTransaction transaction, MoneyTransactionRequestUpdate dto, Account account) {
        if (transaction == null || dto == null || account == null) return;

        transaction.setAmount(dto.amount());
        transaction.setExpensiveType(dto.expensive_type());
        transaction.setDescription(dto.description());
        transaction.setCreatedAt(dto.created_at());
        transaction.setAccount(account);
    }

    public MoneyTransactionResponse toResponseDTO(MoneyTransaction transaction) {
        if (transaction == null) return null;

        return new MoneyTransactionResponse(
                transaction.getId(),
                transaction.getExpensiveType(),
                transaction.getDescription(),
                transaction.getCreatedAt(),
                transaction.getAmount(),
                transaction.getAccount() != null ? transaction.getAccount().getId() : null
        );
    }
}