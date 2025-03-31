package br.com.jota.finance.repositories;

import br.com.jota.finance.entities.BankAccount;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.entities.enums.Perfil;
import br.com.jota.finance.entities.enums.TypeAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Should return null when user has no bank accounts")
    void findBankAccountsByUserIdCena1() {
        User user = createUser("Jota", "jota@gmail.com", "j@v2515", Boolean.TRUE, Perfil.USER, LocalDateTime.now());

        var bankAccount = userRepository.findBankAccountsByUserId(user.getId());

        assertThat(bankAccount.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should return bank accounts when user has accounts")
    void findBankAccountsByUserIdCena2() {
        User user = createUser("Jota", "jota@gmail.com", "j@v2515", Boolean.TRUE, Perfil.USER, LocalDateTime.now());

        BankAccount expect = createAccount("Nu bank", TypeAccount.CURRENT_ACCOUNT, new BigDecimal(200.00), user);

        var actual = userRepository.findBankAccountsByUserId(user.getId());

        assertThat(actual.get(0).getIdBankAccount()).isEqualTo(expect.getIdBankAccount());
    }

    private User createUser(String name, String email, String password, Boolean status, Perfil role, LocalDateTime creationDate) {
        var user = new User(name, email, password, status, role, creationDate);
        testEntityManager.persist(user);
        return user;
    }

    private BankAccount createAccount(String nameAccount, TypeAccount typeAccount, BigDecimal openingBalance, User user) {
        var bankAccount = new BankAccount(nameAccount, typeAccount, openingBalance, user);
        testEntityManager.persist(bankAccount);
        return bankAccount;
    }
}