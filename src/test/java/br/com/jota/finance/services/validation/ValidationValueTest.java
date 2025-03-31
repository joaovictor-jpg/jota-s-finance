package br.com.jota.finance.services.validation;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ValidationValueTest {

    ValidationValue validationValue;

    @BeforeEach
    public void setUp() {
        validationValue = new ValidationValue();
    }

    @Test
    @Description("Should throw an error when the amount in the account is less than the transaction amount")
    void validateSufficientBalanceC1() {

        BigDecimal amountInTheAccount = new BigDecimal(200.00);
        BigDecimal amountInTheTransaction = new BigDecimal(250.00);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validationValue.validateSufficientBalance(amountInTheTransaction, amountInTheAccount), "Expected validationValue() to throw IllegalArgumentException, but it didn't.");

        assertEquals("Transfer amount greater than the amount in the account", exception.getMessage());
    }

    @Test
    @Description("Should not throw an error when the amount in the account is mor than the transaction amount")
    void validateSufficientBalanceC2() {

        BigDecimal amountInTheAccount = new BigDecimal(3000.00);
        BigDecimal amountInTheTransaction = new BigDecimal(250.00);

        assertDoesNotThrow(() -> validationValue.validateSufficientBalance(amountInTheTransaction, amountInTheAccount));

    }
}