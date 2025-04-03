package br.com.jota.finance.services;

import br.com.jota.finance.DTOs.transactionDTO.DataTransaction;
import br.com.jota.finance.entities.BankAccount;
import br.com.jota.finance.entities.Category;
import br.com.jota.finance.entities.Transaction;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.entities.enums.TypeCategoryEnum;
import br.com.jota.finance.entities.enums.TypeTransaction;
import br.com.jota.finance.repositories.BankAccountRepository;
import br.com.jota.finance.repositories.CategoryRepository;
import br.com.jota.finance.repositories.TransactionRepository;
import br.com.jota.finance.services.validation.IValidationValue;
import br.com.jota.finance.services.validation.ValidationValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Description;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Spy
    private List<IValidationValue> validationValues = new ArrayList<>();

    @Mock
    private ValidationValue validationValue;

    @Mock
    private Category category;

    @Mock
    private BankAccount bankAccount;

    @Mock
    private User user;

    @Captor
    private ArgumentCaptor<Transaction> transactionArgumentCaptor;

    @Test
    @Description("Must call method to save transaction")
    void registerTransactionC1() {

        DataTransaction dto = new DataTransaction("Description", new BigDecimal("100.00"), TypeTransaction.INCOME, LocalDateTime.parse("2025-03-10T12:30:00"), 1L, 1L);

        BDDMockito.given(category.getType()).willReturn(TypeCategoryEnum.INCOME);
        BDDMockito.given(categoryRepository.findById(dto.idCategory())).willReturn(Optional.of(category));
        BDDMockito.given(bankAccountRepository.findById(dto.idBankAccount())).willReturn(Optional.of(bankAccount));
        validationValues.add(validationValue);

        transactionService.registerTransaction(dto, user);

        then(transactionRepository).should().save(transactionArgumentCaptor.capture());

        Transaction transactionSalve = transactionArgumentCaptor.getValue();

        Assertions.assertEquals(dto.description(), transactionSalve.getDescription());
        Assertions.assertEquals(category, transactionSalve.getCategory());
        Assertions.assertEquals(bankAccount, transactionSalve.getBankAccount());
    }


    @Test
    @Description("Must call validations")
    void registerTransactionC2() {

        DataTransaction dto = new DataTransaction("Description", new BigDecimal("100.00"), TypeTransaction.INCOME, LocalDateTime.parse("2025-03-10T12:30:00"), 1L, 1L);

        BDDMockito.given(bankAccount.getOpeningBalance()).willReturn(new BigDecimal("2000.00"));
        BDDMockito.given(category.getType()).willReturn(TypeCategoryEnum.INCOME);
        BDDMockito.given(categoryRepository.findById(dto.idCategory())).willReturn(Optional.of(category));
        BDDMockito.given(bankAccountRepository.findById(dto.idBankAccount())).willReturn(Optional.of(bankAccount));
        validationValues.add(validationValue);

        transactionService.registerTransaction(dto, user);

        then(validationValue).should().validateSufficientBalance(dto.transactionValue(), bankAccount.getOpeningBalance());
    }
}