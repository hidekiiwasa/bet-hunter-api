package com.bethunter.bethunter_api.dto.account;

import java.math.BigDecimal;

public record AccountResponse(String id, String id_user, BigDecimal balance) {
}