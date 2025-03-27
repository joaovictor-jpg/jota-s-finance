package br.com.jota.finance.services;

import br.com.jota.finance.DTOs.budgetDTO.BudgetDetails;
import br.com.jota.finance.DTOs.budgetDTO.DataBudget;
import br.com.jota.finance.DTOs.budgetDTO.DataValueBudget;
import br.com.jota.finance.entities.Budget;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.repositories.BudgetRepository;
import br.com.jota.finance.repositories.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;

    public BudgetService(BudgetRepository budgetRepository, CategoryRepository categoryRepository) {
        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
    }

    public BudgetDetails createBudget(DataBudget data, User user) {

        var category = categoryRepository.findById(data.idCategory()).orElseThrow();

        Budget budget = new Budget(data.valueBudget(), data.monthYear(), category, user);

        budgetRepository.save(budget);

        return new BudgetDetails(budget);
    }

    public List<BudgetDetails> findByUser(User user) {
        List<Budget> budgets = budgetRepository.findByUser(user);

        return budgets.stream().map(BudgetDetails::new).toList();
    }

    public BudgetDetails updateValue(Long idBudget, User user, @Valid DataValueBudget data) {
        Budget budget = budgetRepository.findByIdBudgetAndUser(idBudget, user).orElseThrow();

        budget.setValueBudget(data.value());

        budgetRepository.save(budget);

        return new BudgetDetails(budget);
    }
}
