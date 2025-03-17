package br.com.jota.finance.services.validation;

import br.com.jota.finance.DTOs.goalDTOS.GoalData;
import org.springframework.stereotype.Component;

@Component
public class ValidationDate implements Validation{
    @Override
    public void validation(GoalData data) {
        var startDate = data.startDate();
        var endDate = data.endDate();

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("The end date must be a future date, after the start date.");
        }
    }
}
