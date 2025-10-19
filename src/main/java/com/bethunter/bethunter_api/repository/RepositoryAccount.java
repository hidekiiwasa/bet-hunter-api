package com.bethunter.bethunter_api.repository;

import com.bethunter.bethunter_api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAccount extends JpaRepository<Account, String> {
}
