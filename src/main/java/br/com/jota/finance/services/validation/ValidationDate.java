package br.com.jota.finance.services.validation;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidationDate implements IValidationData {
    @Override
    public void validationDate(LocalDateTime endData, LocalDateTime startData) {
        var startDate = startData;
        var endDate = endData;

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("The end date must be a future date, after the start date.");
        }
    }
}
