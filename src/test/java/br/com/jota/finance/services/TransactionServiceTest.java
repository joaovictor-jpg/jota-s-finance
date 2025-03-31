package br.com.jota.finance.services;

import br.com.jota.finance.DTOs.transactionDTO.DataTransaction;
import br.com.jota.finance.DTOs.transactionDTO.DetailTransaction;
import br.com.jota.finance.entities.BankAccount;
import br.com.jota.finance.entities.Category;
import br.com.jota.finance.entities.Transaction;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.entities.enums.Perfil;
import br.com.jota.finance.entities.enums.TypeAccount;
import br.com.jota.finance.entities.enums.TypeCategoryEnum;
import br.com.jota.finance.entities.enums.TypeTransaction;
import br.com.jota.finance.repositories.BankAccountRepository;
import br.com.jota.finance.repositories.CategoryRepository;
import br.com.jota.finance.repositories.TransactionRepository;
import br.com.jota.finance.repositories.UserRepository;
import br.com.jota.finance.services.validation.ValidationValue;
import jakarta.persistence.EntityNotFoundException;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    @Description("Should throw exception when don't have category")
    void registerTransactionC1() {
        User user = createUser("Jota", "jota@gmail.com", "j@v100", Boolean.TRUE, Perfil.USER, LocalDateTime.now());

        DataTransaction transaction = createDataTransaction("Transferencia no mercado", new BigDecimal(200.00), TypeTransaction.INCOME, LocalDateTime.parse("2025-03-10T12:30:00"), 2L, 3L);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> transactionService.registerTransaction(transaction, user), "Expected registerTransaction() to throw EntityNotFoundException, but it didn't.");

        assertEquals("Category not found", exception.getMessage());
    }

    @Test
    @Description("Should throw exception when don't have Bank Account")
    void registerTransactionC2() {

        User user = createUser("Jota", "jota@gmail.com", "j@v100", Boolean.TRUE, Perfil.USER, LocalDateTime.now());

        Category category = new Category("Comida", TypeCategoryEnum.EXPENSE, user);

        when(categoryRepository.findById(anyLong()))
                .thenReturn(Optional.of(category));

        when(bankAccountRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        DataTransaction transaction = createDataTransaction("Transferencia no mercado", new BigDecimal(200.00), TypeTransaction.INCOME, LocalDateTime.parse("2025-03-10T12:30:00"), 1L, 3L);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> transactionService.registerTransaction(transaction, user), "Expected registerTransaction() to throw EntityNotFoundException, but it didn't.");

        assertEquals("bank account not found", exception.getMessage());
    }

    @Test
    @Description("Shoud return DetailTransaction")
    void registerTransactionC3() {

        ValidationValue realValidator = new ValidationValue();

        User user = createUser("Jota", "jota@gmail.com", "j@v100", Boolean.TRUE, Perfil.USER, LocalDateTime.now());

        Category category = new Category("Comida", TypeCategoryEnum.EXPENSE, user);

        BankAccount bankAccount = new BankAccount("Nu Bank", TypeAccount.CURRENT_ACCOUNT, new BigDecimal(2000.00), user);

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(bankAccountRepository.findById(anyLong())).thenReturn(Optional.of(bankAccount));

        //reflection
        Transaction expectedTransaction = new Transaction(
                "Transferencia no mercado",
                new BigDecimal(200.00),
                LocalDateTime.parse("2025-03-10T12:30:00"),
                TypeTransaction.INCOME,
                user,
                category,
                bankAccount
        );
        when(transactionRepository.save(any(Transaction.class))).thenReturn(expectedTransaction);

        DataTransaction transaction = createDataTransaction("Transferencia no mercado", new BigDecimal(200.00), TypeTransaction.INCOME, LocalDateTime.parse("2025-03-10T12:30:00"), 1L, 3L);

        try {
            Field field = TransactionService.class.getDeclaredField("validationValue");
            field.setAccessible(true);
            field.set(transactionService, List.of(realValidator));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        DetailTransaction expect = transactionService.registerTransaction(transaction, user);

        assertThat(expect.description()).isEqualTo(transaction.description());
    }

    private DataTransaction createDataTransaction(String description, BigDecimal transactionValue, TypeTransaction typeTransaction, LocalDateTime transactionDate, Long idCategory, Long idBankAccount) {
        return new DataTransaction(description, transactionValue, typeTransaction, transactionDate, idCategory, idBankAccount);
    }

    private User createUser(String name, String email, String password, Boolean status, Perfil role, LocalDateTime creationDate) {
        var user = new User(name, email, password, status, role, creationDate);
        return user;
    }
}