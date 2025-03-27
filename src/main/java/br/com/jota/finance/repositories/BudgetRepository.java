package br.com.jota.finance.repositories;

import br.com.jota.finance.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
