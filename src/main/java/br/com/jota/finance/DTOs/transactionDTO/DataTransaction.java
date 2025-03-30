package br.com.jota.finance.DTOs.transactionDTO;

import br.com.jota.finance.entities.enums.TypeTransaction;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DataTransaction(
        @NotBlank
        String description,
        @NotNull
        @PositiveOrZero
        @DecimalMin("0.01")
        @Digits(integer = 10, fraction = 2)
        BigDecimal transactionValue,
        @NotNull
        TypeTransaction typeTransaction,
        @NotNull
        @PastOrPresent
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime transactionDate,
        @NotNull
        Long idCategory,
        @NotNull
        Long idBankAccount
) {
}
