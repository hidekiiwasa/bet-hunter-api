package com.bethunter.bethunter_api.dto.moneyTransaction;

import com.bethunter.bethunter_api.enums.Expensives;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record MoneyTransactionRequestCreate(@NotBlank BigDecimal amount, @NotBlank Expensives expensive_type,
                                            @NotBlank String description, @NotBlank String id_account) {
}
