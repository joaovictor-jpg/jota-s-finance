package br.com.jota.finance.repositories;

import br.com.jota.finance.entities.Transaction;
import br.com.jota.finance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);

    Optional<Transaction> findByIdTransactionAndUser(Long idTransaction, User user);
}
