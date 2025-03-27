package br.com.jota.finance.DTOs.budgetDTO;

import br.com.jota.finance.entities.Budget;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BudgetDetails(
        Long idBudget,
        BigDecimal valueBudget,
        LocalDate monthYear
) {
    public BudgetDetails(Budget data) {
        this(data.getIdBudget(), data.getValueBudget(), data.getMonthYear());
    }
}
