package com.bethunter.bethunter_api.repository;

import com.bethunter.bethunter_api.model.MoneyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryMoneyTransaction extends JpaRepository<MoneyTransaction, String> {
}
