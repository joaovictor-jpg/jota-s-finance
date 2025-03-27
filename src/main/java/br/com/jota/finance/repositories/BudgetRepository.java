package br.com.jota.finance.repositories;

import br.com.jota.finance.entities.Budget;
import br.com.jota.finance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUser(User user);
}
