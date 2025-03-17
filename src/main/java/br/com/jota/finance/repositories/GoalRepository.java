package br.com.jota.finance.repositories;

import br.com.jota.finance.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}
