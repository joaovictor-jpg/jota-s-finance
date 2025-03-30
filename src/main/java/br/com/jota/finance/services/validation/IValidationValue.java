package br.com.jota.finance.services.validation;

import java.math.BigDecimal;

public interface IValidationValue {
    void validateSufficientBalance(BigDecimal transactionValue, BigDecimal accountBalance);
}
