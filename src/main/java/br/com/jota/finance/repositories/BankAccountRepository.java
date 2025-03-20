package br.com.jota.finance.repositories;

import br.com.jota.finance.entities.BankAccount;
import br.com.jota.finance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    @Query("SELECT b FROM BankAccount b WHERE b.idBankAccount = :idBankAccount AND b.user = :user")
    Optional<BankAccount> findByUserAndIdBankAccount(User user, Long idBankAccount);
}
