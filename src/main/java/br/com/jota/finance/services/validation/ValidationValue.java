package br.com.jota.finance.services.validation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidationValue implements IValidationValue {
    @Override
    public void validateSufficientBalance(BigDecimal transactionValue, BigDecimal accountBalance) {
        if (transactionValue.compareTo(accountBalance) > 0) {
            throw new IllegalArgumentException("Transfer amount greater than the amount in the account");
        }
    }
}
