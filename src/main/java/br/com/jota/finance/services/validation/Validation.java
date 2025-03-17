package br.com.jota.finance.services.validation;

import br.com.jota.finance.DTOs.goalDTOS.GoalData;
import br.com.jota.finance.DTOs.goalDTOS.GoalUpdateData;

public interface Validation {
    void validation(GoalData data);
    void validation(GoalUpdateData data);
}
