package br.com.jota.finance.DTOs.bankAccountDTOs;

import br.com.jota.finance.entities.enums.TypeAccount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record BankAccountData(
        @NotBlank
        String nameAccount,
        @NotNull
        TypeAccount typeAccount,
        @PositiveOrZero
        BigDecimal openingBalance
) {
}
