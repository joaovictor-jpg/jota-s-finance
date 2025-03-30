package br.com.jota.finance.services;

import br.com.jota.finance.DTOs.categoryDTOs.CategoryDetails;
import br.com.jota.finance.DTOs.transactionDTO.DataTransaction;
import br.com.jota.finance.DTOs.transactionDTO.DetailTransaction;
import br.com.jota.finance.entities.BankAccount;
import br.com.jota.finance.entities.Category;
import br.com.jota.finance.entities.Transaction;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.repositories.BankAccountRepository;
import br.com.jota.finance.repositories.CategoryRepository;
import br.com.jota.finance.repositories.TransactionRepository;
import br.com.jota.finance.services.validation.IValidationValue;
import br.com.jota.finance.services.validation.ValidationValue;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final BankAccountRepository bankAccountRepository;
    private final List<IValidationValue> validationValue = new ArrayList<>();

    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository, BankAccountRepository bankAccountRepository, ValidationValue validationValue) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.validationValue.add(validationValue);
    }

    @Transactional
    public DetailTransaction registerTransaction(DataTransaction data, User user) {
        Category category = categoryRepository.findById(data.idCategory()).orElseThrow();
        BankAccount bankAccount = bankAccountRepository.findById(data.idBankAccount()).orElseThrow();

        Transaction transaction = new Transaction(data.description(), data.transactionValue(), data.transactionDate(), data.typeTransaction(), user, category, bankAccount);

        validationValue.forEach(v -> v.validateSufficientBalance(transaction.getTransactionValue(), bankAccount.getOpeningBalance()));

        transactionRepository.save(transaction);

        return new DetailTransaction(transaction, new CategoryDetails(category));
    }

    public List<DetailTransaction> listTransaction(User user) {
        List<Transaction> transactions = transactionRepository.findByUser(user);
        return transactions.stream().map(transaction -> new DetailTransaction(transaction, new CategoryDetails(transaction.getCategory()))).toList();
    }

    public void delete(Long idTransaction, User user) {
        Transaction transaction = transactionRepository.findByIdTransactionAndUser(idTransaction, user).orElseThrow();
        transactionRepository.delete(transaction);
    }
}
