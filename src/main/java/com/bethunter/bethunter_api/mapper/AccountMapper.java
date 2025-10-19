package com.bethunter.bethunter_api.mapper;

import com.bethunter.bethunter_api.dto.account.*;
import com.bethunter.bethunter_api.model.Account;
import com.bethunter.bethunter_api.model.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountMapper {

    public Account toEntity(AccountRequestCreate dto, User user) {
        if (dto == null || user == null) return null;
        Account account = new Account();
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);
        return account;
    }

    public Account toEntity(AccountRequestUpdate dto, User user) {
        if (dto == null || user == null) return null;
        Account account = new Account();
        account.setUser(user);
        account.setBalance(dto.balance());
        return account;
    }

    public void updateEntity(Account account, AccountRequestUpdate dto, User user) {
        if (account == null || dto == null || user == null) return;
        account.setUser(user);
        account.setBalance(dto.balance());
    }

    public AccountResponse toResponseDTO(Account account) {
        if (account == null) return null;
        return new AccountResponse(
                account.getId(),
                account.getUser() != null ? account.getUser().getId() : null,
                account.getBalance()
        );
    }
}