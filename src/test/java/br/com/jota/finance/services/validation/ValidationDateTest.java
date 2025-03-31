package br.com.jota.finance.services.validation;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ValidationDateTest {

    private ValidationDate validationDate;

    @BeforeEach
    public void setUp() {
        validationDate = new ValidationDate();
    }

    @Test
    @Description("Should throw exception when end date is before start date")
    void validationC1() {
        LocalDateTime startDate = LocalDateTime.parse("2025-05-01T12:30:00");
        LocalDateTime endDate = LocalDateTime.parse("2025-04-30T12:30:00");


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validationDate.validationDate(endDate, startDate), "Expected validation() to throw IllegalArgumentException, but it didn't.");

        assertEquals("The end date must be a future date, after the start date.", exception.getMessage());
    }

    @Test
    @Description("Should not throw exception when end date is after start date")
    void validationC2() {

        LocalDateTime startDate = LocalDateTime.parse("2025-05-01T12:30:00");
        LocalDateTime endDate = LocalDateTime.parse("2025-05-02T12:30:00");

        assertDoesNotThrow(() -> validationDate.validationDate(endDate, startDate));
    }
}