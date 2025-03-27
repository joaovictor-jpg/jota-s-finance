package br.com.jota.finance.DTOs.budgetDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record DataValueBudget(
        @NotNull
        @PositiveOrZero
        BigDecimal value
) {
}
