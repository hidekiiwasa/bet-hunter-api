package com.bethunter.bethunter_api.service;

import com.bethunter.bethunter_api.dto.account.AccountRequestCreate;
import com.bethunter.bethunter_api.dto.account.AccountRequestUpdate;
import com.bethunter.bethunter_api.dto.account.AccountResponse;
import com.bethunter.bethunter_api.exception.UserNotFound;
import com.bethunter.bethunter_api.mapper.AccountMapper;
import com.bethunter.bethunter_api.model.Account;
import com.bethunter.bethunter_api.repository.RepositoryAccount;
import com.bethunter.bethunter_api.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAccount {

    @Autowired
    private RepositoryAccount repositoryAccount;

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private AccountMapper accountMapper;

    public AccountResponse createAccount(AccountRequestCreate dto) {
        return repositoryUser.findById(dto.id_user())
                .map(user -> {
                    Account account = accountMapper.toEntity(dto, user);
                    return accountMapper.toResponseDTO(repositoryAccount.save(account));
                })
                .orElseThrow(() -> new UserNotFound());
    }

    public List<AccountResponse> findAll() {
        return repositoryAccount.findAll().stream()
                .map(accountMapper::toResponseDTO)
                .toList();
    }

    public Optional<AccountResponse> findById(String id) {
        return repositoryAccount.findById(id)
                .map(accountMapper::toResponseDTO);
    }

    public Optional<AccountResponse> update(String id, AccountRequestUpdate dto) {
        return repositoryUser.findById(dto.id_user())
                .flatMap(user -> repositoryAccount.findById(id)
                        .map(account -> {
                            accountMapper.updateEntity(account, dto, user);
                            Account updated = repositoryAccount.save(account);
                            return accountMapper.toResponseDTO(updated);
                        })
                );
    }

    public boolean delete(String id) {
        if (repositoryAccount.existsById(id)) {
            repositoryAccount.deleteById(id);
            return true;
        }

        return false;
    }
}
