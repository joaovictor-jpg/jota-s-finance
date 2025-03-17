package br.com.jota.finance.repositories;

import br.com.jota.finance.entities.Goal;
import br.com.jota.finance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    @Query("SELECT g FROM Goal g WHERE g.idGoal = :idGoal AND g.user = :user")
    Optional<Goal> findByUserAndIdGoal(User user, Long idGoal);
}
