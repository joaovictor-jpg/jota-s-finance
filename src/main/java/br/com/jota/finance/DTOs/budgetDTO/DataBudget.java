package br.com.jota.finance.DTOs.budgetDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DataBudget(
        @NotNull
        @PositiveOrZero
        BigDecimal valueBudget,
        @NotNull
        @Future
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate monthYear,
        @NotNull
        Long idCategory
) {
}
