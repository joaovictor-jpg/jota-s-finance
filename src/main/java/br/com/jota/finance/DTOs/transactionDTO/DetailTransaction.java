package br.com.jota.finance.DTOs.transactionDTO;

import br.com.jota.finance.DTOs.categoryDTOs.CategoryDetails;
import br.com.jota.finance.entities.Transaction;
import br.com.jota.finance.entities.enums.TypeTransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DetailTransaction(
        Long idTransaction,
        String description,
        BigDecimal transactionValue,
        LocalDateTime transactionDate,
        TypeTransaction type,
        CategoryDetails categoryDetails
) {
    public DetailTransaction(Transaction transaction, CategoryDetails categoryDetails) {
        this(transaction.getIdTransaction(), transaction.getDescription(), transaction.getTransactionValue(), transaction.getTransactionDate(), transaction.getType(), categoryDetails);
    }
}
