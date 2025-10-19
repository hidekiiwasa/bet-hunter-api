package com.bethunter.bethunter_api.dto.account;

import jakarta.validation.constraints.NotBlank;

public record AccountRequestCreate(@NotBlank String id_user) {
}
