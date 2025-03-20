package br.com.jota.finance.repositories;

import br.com.jota.finance.entities.BankAccount;
import br.com.jota.finance.entities.Goal;
import br.com.jota.finance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailIgnoreCase(String email);
    @Query("SELECT g FROM Goal g WHERE g.user.id = :id")
    List<Goal> findGoalsByUserId(Long id);
    @Query("SELECT b FROM BankAccount b WHERE b.user.id = :id")
    List<BankAccount> findBankAccountsByUserId(Long id);
}
