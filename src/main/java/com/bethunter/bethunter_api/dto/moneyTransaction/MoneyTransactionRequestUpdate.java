package com.bethunter.bethunter_api.dto.moneyTransaction;

import com.bethunter.bethunter_api.enums.Expensives;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MoneyTransactionRequestUpdate(Expensives expensive_type, String description, LocalDate created_at,
                                            BigDecimal amount, String id_account) {
}
