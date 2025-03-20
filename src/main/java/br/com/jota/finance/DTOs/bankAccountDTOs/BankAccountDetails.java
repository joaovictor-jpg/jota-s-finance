package br.com.jota.finance.DTOs.bankAccountDTOs;

import br.com.jota.finance.entities.BankAccount;
import br.com.jota.finance.entities.enums.TypeAccount;

import java.math.BigDecimal;

public record BankAccountDetails(
        Long id,
        String nameAccount,
        TypeAccount typeAccount,
        BigDecimal openingBalance
) {
    public BankAccountDetails(BankAccount bankAccount) {
        this(bankAccount.getIdBankAccount(), bankAccount.getNameAccount(), bankAccount.getTypeAccount(), bankAccount.getOpeningBalance());
    }
}
