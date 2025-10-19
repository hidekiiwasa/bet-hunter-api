package com.bethunter.bethunter_api.dto.account;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record AccountRequestUpdate(@NotBlank String id_user, BigDecimal balance) {
}
