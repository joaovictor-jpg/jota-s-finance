package br.com.jota.finance.services.validation;

import br.com.jota.finance.DTOs.goalDTOS.GoalData;
import br.com.jota.finance.DTOs.goalDTOS.GoalUpdateData;
import org.springframework.stereotype.Component;

@Component
public class ValidationDate implements IValidationData {
    @Override
    public void validation(GoalData data) {
        var startDate = data.startDate();
        var endDate = data.endDate();

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("The end date must be a future date, after the start date.");
        }
    }

    @Override
    public void validation(GoalUpdateData data) {
        var startDate = data.startDate();
        var endDate = data.endDate();

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("The end date must be a future date, after the start date.");
        }
    }
}
