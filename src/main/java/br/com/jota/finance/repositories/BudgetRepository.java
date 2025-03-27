package br.com.jota.finance.repositories;

import br.com.jota.finance.entities.Budget;
import br.com.jota.finance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUser(User user);

    Optional<Budget> findByIdBudgetAndUser(Long idBudget, User user);
}
